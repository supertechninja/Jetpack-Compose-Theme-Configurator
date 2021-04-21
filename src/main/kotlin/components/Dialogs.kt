package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogDemo() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            showAlertDialog = !showAlertDialog
        }, modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Show Alert Dialog")
        }

        Button(onClick = {
            showAlertDialog = !showAlertDialog
        }, modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Show Dialog")
        }

        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = {
                    showAlertDialog = !showAlertDialog
                }, title = {
                    Text("Alert Title")
                }, text = {
                    Text("Alert dialog body text")
                }, confirmButton = {
                    TextButton(onClick = {
                        showAlertDialog = !showAlertDialog
                    }) {
                        Text("Ok")
                    }
                }, dismissButton = {
                    TextButton(onClick = {
                        showAlertDialog = !showAlertDialog
                    }) {
                        Text("Cancel")
                    }
                }, properties = DialogProperties(title = "Sample Alert Dialog"),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
