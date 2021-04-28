import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItems() {
    var primaryText by remember { mutableStateOf("Primary Text of List Item") }

    var addIcon by remember { mutableStateOf(false) }

    var addSecondaryText by remember { mutableStateOf(false) }
    var secondaryText by remember { mutableStateOf("I am secondary text") }

    var addOverlineText by remember { mutableStateOf(false) }
    var overlineText by remember { mutableStateOf("Overline Text goes here") }

    var hasDivider by remember { mutableStateOf(false) }

    var addTrailingContent by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val listItemIcon: @Composable (() -> Unit)? = if (addIcon) {
        @Composable {
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.TrackChanges, contentDescription = "")
            }
        }
    } else null

    val listItemSecondaryText: @Composable (() -> Unit)? = if (addSecondaryText) {
        @Composable {
            Text(secondaryText)
        }
    } else null

    val listItemOverlineText: @Composable (() -> Unit)? = if (addOverlineText) {
        @Composable {
            Text(overlineText)
        }
    } else null

    var listItemTrailingContent: @Composable (() -> Unit)? = if (addTrailingContent) {
        @Composable {
            var isChecked by remember { mutableStateOf(true) }
            Checkbox(isChecked, onCheckedChange = { isChecked = !isChecked })
        }
    } else null

    Column(modifier = Modifier.verticalScroll(scrollState).padding(bottom = 16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Card(modifier = Modifier.padding(16.dp), elevation = 8.dp) {
                Column() {
                    ListItem(
                        icon = listItemIcon,
                        secondaryText = listItemSecondaryText,
                        overlineText = listItemOverlineText,
                        trailing = listItemTrailingContent
                    ) {
                        Text(primaryText)
                    }

                    if (hasDivider) {
                        Divider()
                        ListItem(
                            icon = listItemIcon,
                            secondaryText = listItemSecondaryText,
                            overlineText = listItemOverlineText,
                            trailing = listItemTrailingContent
                        ) {
                            Text(primaryText)
                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = primaryText,
            onValueChange = {
                primaryText = it
            },
            label = {
                Text("Primary Text")
            },
            modifier = Modifier.padding(start = 32.dp)
        )

        OptionRow(
            title = "Show Icon",
            checked = addIcon,
            onCheckedChange = { addIcon = it }
        )

        OptionRow(
            title = "Add Secondary Text",
            checked = addSecondaryText,
            onCheckedChange = { addSecondaryText = it }
        )

        if (addSecondaryText) {
            OutlinedTextField(
                value = secondaryText,
                onValueChange = {
                    secondaryText = it
                },
                label = {
                    Text("Secondary Text")
                },
                modifier = Modifier.padding(start = 32.dp)
            )
        }

        OptionRow(
            title = "Add Overline Text",
            checked = addOverlineText,
            onCheckedChange = { addOverlineText = it }
        )

        if (addOverlineText) {
            OutlinedTextField(
                value = overlineText,
                onValueChange = {
                    overlineText = it
                },
                label = {
                    Text("Overline Text")
                },
                modifier = Modifier.padding(start = 32.dp)
            )
        }

        OptionRow(
            title = "Add Trailing Control",
            checked = addTrailingContent,
            onCheckedChange = { addTrailingContent = it }
        )

        OptionRow(
            title = "Divider",
            checked = hasDivider,
            onCheckedChange = { hasDivider = it }
        )
    }
}