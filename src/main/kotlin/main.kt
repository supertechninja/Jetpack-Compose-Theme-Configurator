import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import theme.Blue500
import theme.ComposeComponentsTheme
import theme.Yellow700

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun main() = Window {
    var selectedComponent by remember { mutableStateOf(Components.NONE) }
    var showThemeSettings by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var primaryColor by remember { mutableStateOf(Blue500) }
    val primaryColorState = rememberColorState(color = primaryColor, updateColor = {
        primaryColor = it
        showDialog = false
    })

    var secondaryColor by remember { mutableStateOf(Yellow700) }
    val secondaryColorState = rememberColorState(color = secondaryColor, updateColor = {
        secondaryColor = it
        showDialog = false
    })

    var colorStateToUpdate = rememberColorState {}

    var title = if (showThemeSettings) "Theme" else "Compose Components"

    var backdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()

//    val darkTheme = isSystemInDarkTheme()
    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    ComposeComponentsTheme(
        if (isDarkThemeEnabled) {
            getDarkColorPalette(primaryColor = primaryColor, secondaryColor = secondaryColor)
        } else {
            getLightColorPalette(primaryColor = primaryColor, secondaryColor = secondaryColor)
        }
    ) {
        BackdropScaffold(
            scaffoldState = backdropScaffoldState,
            frontLayerShape = RectangleShape,
            frontLayerScrimColor = Color.Transparent,
            appBar = {
                TopAppBar(
                    title = {
                        Text(text = title, color = Color.White)
                    },
                    elevation = 8.dp,
                    actions = {
                        IconButton(onClick = {
                            showThemeSettings = !showThemeSettings
                            if (showThemeSettings) {
                                coroutineScope.launch {
                                    backdropScaffoldState.reveal()
                                }
                            } else {
                                coroutineScope.launch {
                                    backdropScaffoldState.conceal()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Settings, tint = Color.White, contentDescription = "")
                        }

                        IconButton(onClick = {
                            isDarkThemeEnabled = !isDarkThemeEnabled
                        }) {
                            Icon(imageVector = Icons.Default.DarkMode, tint = Color.White, contentDescription = "")
                        }
                    }
                )

            },
            frontLayerElevation = 16.dp,
            frontLayerContent = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row {
                        Column(
                            modifier = Modifier.fillMaxHeight().width(220.dp)
                                .background(MaterialTheme.colors.surface)
                                .padding(bottom = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            LazyColumn() {
                                items(Components.values().toList()) {
                                    val backgroundColor =
                                        if (it == selectedComponent) {
                                            Color.LightGray
                                        } else MaterialTheme.colors.surface
                                    if (it != Components.NONE) {
                                        ListItem(
                                            modifier = Modifier.clickable(onClick = {
                                                selectedComponent = it
                                            }).background(color = backgroundColor),
                                            text = {
                                                Text(it.componentName, color = MaterialTheme.colors.onSurface)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        Divider(modifier = Modifier.width(0.5.dp).fillMaxHeight())

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))

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
                                }
                                Components.FAB -> TODO()
                                Components.DIALOGS -> TODO()
                                Components.BOTTOM_APP_BAR -> TODO()
                                Components.CARDS -> TODO()
                                Components.MENUS -> TODO()
                            }
                        }
                    }

                    AnimatedVisibility(showDialog) {
                        ColorPicker(colorStateToUpdate)
                    }
                }
            },
            backLayerContent = {
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                        .background(color = MaterialTheme.colors.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(48.dp).clickable(
                                onClick = {
                                    colorStateToUpdate = primaryColorState
                                    showDialog = true
                                }),
                            shape = CircleShape,
                            color = primaryColorState.color
                        ) {}

                        Text(
                            "Primary Color",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    Row(
                        modifier = Modifier.padding(16.dp).wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(48.dp).clickable(
                                onClick = {
                                    colorStateToUpdate = secondaryColorState
                                    showDialog = true
                                }),
                            shape = CircleShape,
                            color = secondaryColorState.color
                        ) {}

                        Text(
                            "Secondary Color",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        )
    }
}

class ColorState(
    val color: Color,
    val updateColor: (Color) -> Unit
)

@Composable
fun rememberColorState(
    color: Color = Color.Yellow, updateColor: (Color) -> Unit = {}
) = ColorState(
    color = color,
    updateColor = updateColor
)

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

fun getLightColorPalette(primaryColor: Color, secondaryColor: Color) = lightColors(
    primary = primaryColor,
    primaryVariant = primaryColor,
    secondary = secondaryColor,
    secondaryVariant = primaryColor,
)

fun getDarkColorPalette(primaryColor: Color, secondaryColor: Color) = darkColors(
    primary = primaryColor,
    primaryVariant = primaryColor,
    secondary = secondaryColor,
    secondaryVariant = primaryColor,
)