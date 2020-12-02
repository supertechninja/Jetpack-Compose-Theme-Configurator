import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListItems() {
    var addLabel by savedInstanceState { false }
    var labelText by savedInstanceState { "" }

    var addSubLabel by savedInstanceState { false }
    var subLabelText by savedInstanceState { "" }

    var addValue by savedInstanceState { false }
    var valueText by savedInstanceState { "" }

    var addSubValue by savedInstanceState { false }
    var subValueText by savedInstanceState { "" }


    Column {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.preferredWidth(300.dp)) {
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
            Column(modifier = Modifier.preferredWidth(200.dp)) {
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
            Column(modifier = Modifier.preferredWidth(200.dp)) {
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