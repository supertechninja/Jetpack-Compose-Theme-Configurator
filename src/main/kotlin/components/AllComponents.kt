package components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllComponentsDemo() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.padding(bottom = 16.dp),
        topBar = {
            TopAppBar(title = {
                Text("All Components")
            })
        },
        content = {
            Column(modifier = Modifier.padding(it).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    var isCheckboxCheck by remember { mutableStateOf(true) }
                    var isSwitchEnabled by remember { mutableStateOf(true) }
                    var isRadioButtonSelected by remember { mutableStateOf(true) }

                    Checkbox(isCheckboxCheck, onCheckedChange = {
                        isCheckboxCheck = !isCheckboxCheck
                    })

                    Switch(isSwitchEnabled, onCheckedChange = {
                        isSwitchEnabled = !isSwitchEnabled
                    })

                    RadioButton(isRadioButtonSelected, onClick = {
                        isRadioButtonSelected = !isRadioButtonSelected
                    })
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {}) {
                        Text("Text Button")
                    }
                    OutlinedButton(onClick = {}) {
                        Text("Outlined Button")
                    }
                    Button(onClick = {}) {
                        Text("Button")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FloatingActionButton(onClick = {}) {
                        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add something")
                            Text("Add something", modifier = Modifier.padding(start = 4.dp))
                        }
                    }

                    FloatingActionButton(onClick = {}, shape = CircleShape) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add something",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var outlinedTextField by remember { mutableStateOf(TextFieldValue("Sample Input")) }
                    OutlinedTextField(value = outlinedTextField, label = {
                        Text("Outlined Input")
                    }, modifier = Modifier.width(150.dp), onValueChange = {
                        outlinedTextField = it
                    })

                    var textField by remember { mutableStateOf(TextFieldValue("Filled Input")) }
                    TextField(value = textField, label = {
                        Text("Filled Input")
                    }, modifier = Modifier.width(150.dp), onValueChange = {
                        textField = it
                    })
                }

                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var isEnabled by remember { mutableStateOf(true) }
                    ListItem(
                        overlineText = {
                            Text("Overline Text")
                        },
                        secondaryText = {
                            Text("List Item Secondary Text")
                        },
                        icon = {
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Default.Hail, contentDescription = "Hail")
                            }
                        },
                        trailing = {
                            Switch(checked = isEnabled, onCheckedChange = { isEnabled = !isEnabled })
                        }
                    ) {
                        Text("List item Primary Test")
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                BottomNavigationItem(selected = selectedTab == 0, onClick = {
                    selectedTab = 0
                }, icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }, label = {
                    Text("Tab 1")
                })
                BottomNavigationItem(selected = selectedTab == 1, onClick = {
                    selectedTab = 1
                }, icon = {
                    Icon(imageVector = Icons.Default.Contacts, contentDescription = "Contacts")
                }, label = {
                    Text("Tab 2")
                })
                BottomNavigationItem(selected = selectedTab == 2, onClick = {
                    selectedTab = 2
                }, icon = {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                }, label = {
                    Text("Tab 3")
                })
            }
        }
    )
}