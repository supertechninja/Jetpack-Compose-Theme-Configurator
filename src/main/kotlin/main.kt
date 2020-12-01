import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

fun main() = Window {
    var selectedComponent by remember { mutableStateOf("") }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("Compose Components", color = Color.White)
                }, backgroundColor = Color.Black,
                actions = {
                    IconButton(onClick = {

                    }){
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
                                .padding(top = 8.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            val listOfComponents = mutableListOf(
                                "Top App Bar",
                                "Buttons",
                                "TextFields",
                                "ListItems",
                                "Tabs",
                                "Bottom Navigation",
                                "Floating Action Buttons",
                                "Dialogs",
                                "Bottom App Bar",
                                "Cards",
                                "Menus"
                            )
                            LazyColumnFor(items = listOfComponents) {
                                val backgroundColor =
                                    if(it == selectedComponent) {Color.LightGray} else Color.White
                                ListItem(modifier = Modifier.clickable(onClick = {
                                    selectedComponent = it
                                }).background(color = backgroundColor)) {
                                    Text(it)
                                }
                            }
                        }
                        Divider(modifier = Modifier.preferredWidth(0.5.dp).fillMaxHeight())

                        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
                            Spacer(modifier = Modifier.preferredHeight(20.dp))

                            when (selectedComponent) {
                                "Top App Bar" -> {
                                    TopAppBarDemo()
                                }
                                "Buttons" -> {
                                    Buttons()
                                }
                                "TextFields" -> {
                                    TextFields()
                                }
                            }
                        }
                    }
                }
            })
    }
}

@Composable
fun showSnackbar() {
}



//Scaffold(
//topBar = {
//    TopAppBar(title = {
//        Text("Compose Desktop")
//    })
//
//},
//bodyContent = {
//    Text("Hello World")
//
//},
//bottomBar = {
//    BottomAppBar {
//        BottomNavigationItem(
//            icon = {
//                Icon(imageVector = Icons.Default.Home)
//            },
//            selected = true,
//            label = {
//                Text("Home")
//            },
//            alwaysShowLabels = true,
//            onClick = {
//
//            }
//        )
//        BottomNavigationItem(
//            icon = {
//                Icon(imageVector = Icons.Default.Info)
//            },
//            selected = true,
//            label = {
//                Text("Exercises")
//            },
//            alwaysShowLabels = true,
//            onClick = {
//
//            }
//        )
//        BottomNavigationItem(
//            icon = {
//                Icon(imageVector = Icons.Default.Delete)
//            },
//            selected = true,
//            label = {
//                Text("Profile")
//            },
//            alwaysShowLabels = true,
//            onClick = {
//
//            }
//        )
//    }
//}
//)