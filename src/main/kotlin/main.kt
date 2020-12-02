import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.ComposeComponentsTheme

fun main() = Window {
    var selectedComponent by remember { mutableStateOf(Components.NONE) }
    var showThemeSettings = remember { mutableStateOf(false) }

    ComposeComponentsTheme {
        if (showThemeSettings.value) {
            ThemeConfig(showThemeSettings)
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text("Compose Components", color = Color.White)
                    }, backgroundColor = Color.Black,
                        actions = {
                            IconButton(onClick = {
                                showThemeSettings.value = true
                            }) {
                                Icon(imageVector = Icons.Default.Settings, tint = Color.White)
                            }
                        })

                },
                bodyContent = {
                    Box {
                        Row {
                            Column(
                                modifier = Modifier.fillMaxHeight().preferredWidth(220.dp)
                                    .background(MaterialTheme.colors.surface)
                                    .padding(bottom = 8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
//                                val listOfComponents = mutableListOf(
//                                    "Top App Bar",
//                                    "Buttons",
//                                    "TextFields",
//                                    "ListItems",
//                                    "Tabs",
//                                    "Bottom Navigation",
//                                    "Floating Action Buttons",
//                                    "Dialogs",
//                                    "Bottom App Bar",
//                                    "Cards",
//                                    "Menus"
//                                )
                                LazyColumnFor(items = Components.values().toList()) {
                                    val backgroundColor =
                                        if (it == selectedComponent) {
                                            Color.LightGray
                                        } else Color.White
                                    if (it != Components.NONE) {
                                        ListItem(modifier = Modifier.clickable(onClick = {
                                            selectedComponent = it
                                        }).background(color = backgroundColor)) {
                                            Text(it.componentName)
                                        }
                                    }
                                }
                            }
                            Divider(modifier = Modifier.preferredWidth(0.5.dp).fillMaxHeight())

                            Column(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                            ) {
                                Spacer(modifier = Modifier.preferredHeight(20.dp))

                                when (selectedComponent) {
                                    Components.NONE -> {

                                    }
                                    Components.TOP_APP_BAR -> {
                                        TopAppBarDemo()
                                    }
                                    Components.BUTTONS -> {
                                        Buttons()
                                    }
                                    Components.TEXTFIELDS -> {
                                        TextFields()
                                    }
                                    Components.LISTITEMS -> {
                                        ListItems()
                                    }
                                    Components.TABS -> TODO()
                                    Components.BOTTOM_NAV -> {
                                        BottomNavigation()
                                    }
                                    Components.FAB -> TODO()
                                    Components.DIALOGS -> TODO()
                                    Components.BOTTOM_APP_BAR -> TODO()
                                    Components.CARDS -> TODO()
                                    Components.MENUS -> TODO()
                                }
                            }
                        }
                    }
                })
        }
    }
}

private enum class Components(val componentName: String) {
    NONE(""),
    TOP_APP_BAR("Top App Bar"),
    BUTTONS("Buttons"),
    TEXTFIELDS("TextFields"),
    LISTITEMS("ListItems"),
    TABS("Tabs"),
    BOTTOM_NAV("Bottom Navigation"),
    FAB("Floating Action Buttons"),
    DIALOGS("Dialogs"),
    BOTTOM_APP_BAR("Bottom App Bar"),
    CARDS("Cards"),
    MENUS("Menus")
}