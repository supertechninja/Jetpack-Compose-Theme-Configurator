import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Buttons() {
    Column {
        var selectedButton by savedInstanceState { ButtonType.Filled }
        var buttonText by savedInstanceState { TextFieldValue("Sample Button Text") }

        val button: @Composable () -> Unit = @Composable {
            when (selectedButton) {
                ButtonType.Filled -> {
                    Button(onClick = {}) {
                        Text(buttonText.text)
                    }
                }
                ButtonType.Outlined -> {
                    OutlinedButton(onClick = {}) {
                        Text(buttonText.text)
                    }
                }
                ButtonType.Text -> {
                    TextButton(onClick = {}) {
                        Text(buttonText.text)
                    }
                }
            }
        }

        Box(
            Modifier.preferredHeight(80.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            button()
        }

        Column {
            OutlinedTextField(
                value = buttonText,
                onValueChange = {
                    buttonText = it
                },
                label = {
                    Text("Button Text")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Title("Button Type")

            Column {
                ButtonType.values().map { it.name }
                    .forEach { textType ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (textType == selectedButton.name),
                                    onClick = {
                                        selectedButton =
                                            ButtonType.valueOf(
                                                textType
                                            )
                                    }
                                )
                                .padding(horizontal = 16.dp)
                        ) {
                            RadioButton(
                                selected = (textType == selectedButton.name),
                                onClick = {
                                    selectedButton =
                                        ButtonType.valueOf(
                                            textType
                                        )
                                },
                                modifier = Modifier.align(
                                    Alignment.CenterVertically
                                )
                            )
                            Text(
                                text = textType,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp).align(
                                    Alignment.CenterVertically
                                )
                            )
                        }
                    }
            }
        }
    }
}

private enum class ButtonType { Filled, Outlined, Text }