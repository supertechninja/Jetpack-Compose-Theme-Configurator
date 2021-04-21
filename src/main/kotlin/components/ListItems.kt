import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItems() {
    var addLabel by remember { mutableStateOf(false ) }
    var labelText by remember { mutableStateOf("") }

    var addSubLabel by remember { mutableStateOf(false ) }
    var subLabelText by remember { mutableStateOf("") }

    var addValue by remember { mutableStateOf(false ) }
    var valueText by remember { mutableStateOf("") }

    var addSubValue by remember { mutableStateOf(false ) }
    var subValueText by remember { mutableStateOf("") }


    Column {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(300.dp)) {
            Row {
                Text(labelText)
                Text(valueText)
            }
            Row {
                Text(subLabelText)
                Text(subValueText)
            }
        }

        Row(modifier = Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)) {
            Column(modifier = Modifier.width(200.dp)) {
                OptionRow(
                    title = "Label",
                    checked = addLabel,
                    onCheckedChange = { addLabel = it }
                )

                if (addLabel) {
                    OutlinedTextField(
                        value = labelText,
                        onValueChange = {
                            labelText = it
                        },
                        label = {
                            Text("Label Text")
                        },
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                OptionRow(
                    title = "SubLabel",
                    checked = addSubLabel,
                    onCheckedChange = { addSubLabel = it }
                )

                if (addSubLabel) {
                    OutlinedTextField(
                        value = subLabelText,
                        onValueChange = {
                            subLabelText = it
                        },
                        label = {
                            Text("SubLabel Text")
                        },
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Column(modifier = Modifier.width(200.dp)) {
                OptionRow(
                    title = "Value",
                    checked = addValue,
                    onCheckedChange = { addValue = it }
                )

                if (addValue) {
                    OutlinedTextField(
                        value = valueText,
                        onValueChange = {
                            valueText = it
                        },
                        label = {
                            Text("Value Text")
                        },
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                OptionRow(
                    title = "SubValue",
                    checked = addSubValue,
                    onCheckedChange = { addSubValue = it }
                )

                if (addSubValue) {
                    OutlinedTextField(
                        value = subValueText,
                        onValueChange = {
                            subValueText = it
                        },
                        label = {
                            Text("SubValue Text")
                        },
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }

}