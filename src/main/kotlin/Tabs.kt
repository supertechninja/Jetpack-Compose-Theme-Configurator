import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabsDemo() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            TabRow(
                modifier = Modifier.requiredHeight(48.dp),
                selectedTabIndex = selectedTabIndex,
                tabs = {
                    Tab(
                        selected = selectedTabIndex == 0,
                        onClick = {
                            selectedTabIndex = 0
                        }
                    ) {
                        Text("Tab 1")
                    }
                    Tab(selected = selectedTabIndex == 1,
                        onClick = {
                            selectedTabIndex = 1
                        }
                    ) {
                        Text("Tab 2")
                    }
                    Tab(selected = selectedTabIndex == 2,
                        onClick = {
                            selectedTabIndex = 2
                        }
                    ) {
                        Text("Tab 3")
                    }
                }
            )
        }
    }
}