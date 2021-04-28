package components

import OptionRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonDemo() {
    var showIcon by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        FloatingActionButton(onClick = {}) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp, 8.dp)) {
                if(showIcon) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
                if(showText) {
                    Text("Add Element", modifier = Modifier.padding(horizontal = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OptionRow("Show Icon", checked = showIcon, onCheckedChange = {
            showIcon = !showIcon
        })

        OptionRow("Show Text", checked = showText, onCheckedChange = {
            showText = !showText
        })
    }
}