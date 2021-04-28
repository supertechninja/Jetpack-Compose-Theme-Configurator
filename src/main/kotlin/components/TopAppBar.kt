import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuItem
import kotlin.random.Random

@Composable
fun TopAppBarDemo() {
    var appBarTitle by remember { mutableStateOf(TextFieldValue("App Bar Title")) }
    var navIcon by remember { mutableStateOf(false) }
    var menuItems by remember { mutableStateOf(false) }
    var numberOfMenuItems by remember { mutableStateOf(1f) }
    var showDropDownMenu by remember { mutableStateOf(false) }

    val navIconComposable: @Composable (() -> Unit)? = if (navIcon) {
        @Composable {
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    } else null

    Column {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            TopAppBar(
                title = { Text(appBarTitle.text) },
                navigationIcon = navIconComposable,
                actions = {
                    if (menuItems) {
                        if (numberOfMenuItems.toInt() <= 3) {
                            repeat(numberOfMenuItems.toInt()) {
                                IconButton(
                                    onClick = {

                                    }) {
                                    Icon(
                                        imageVector = listOfIcons[it],
                                        contentDescription = ""
                                    )
                                }
                            }
                        } else {
                            repeat(2) {
                                IconButton(
                                    onClick = {

                                    }) {
                                    Icon(
                                        imageVector = listOfIcons[it],
                                        contentDescription = ""
                                    )
                                }
                            }

                            IconButton(
                                onClick = {
                                    showDropDownMenu = !showDropDownMenu
                                }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                },
                elevation = 8.dp
            )

            if (showDropDownMenu) {
                Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Top) {
                    DropdownMenu(
                        expanded = true,
                        onDismissRequest = {
                            showDropDownMenu = !showDropDownMenu

                        },
                    ) {
                        var index = 3
                        repeat(numberOfMenuItems.toInt() - 2) {
                            Row() {
                                Text(
                                    text = listOfIcons[index].name,
                                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                                )
                            }
                            index = index.inc()
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(120.dp))

        OutlinedTextField(
            value = appBarTitle,
            onValueChange = {
                appBarTitle = it
            },
            label = {
                Text("App Bar Title")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OptionRow(
            title = "Navigation icon",
            checked = navIcon,
            onCheckedChange = { navIcon = it }
        )

        OptionRow(
            title = "Menu Items",
            checked = menuItems,
            onCheckedChange = { menuItems = it }
        )

        if (menuItems) {
            Text("Number of Menu Items:", modifier = Modifier.padding(top = 20.dp, start = 32.dp))

            val range = 1f..5f
            Slider(
                value = numberOfMenuItems,
                onValueChange = {
                    numberOfMenuItems = it
                },
                valueRange = range,
                steps = 3,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

    }
}

val listOfIcons =
    mutableListOf(
        Icons.Default.Add,
        Icons.Default.Delete,
        Icons.Default.Done,
        Icons.Default.Edit,
        Icons.Default.Info,
        Icons.Default.Search
    )