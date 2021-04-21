package components

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonDemo() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        FloatingActionButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        FloatingActionButton(onClick = {}) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp, 8.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Text("Add Element", modifier = Modifier.padding(horizontal = 4.dp))
            }

        }
    }
}