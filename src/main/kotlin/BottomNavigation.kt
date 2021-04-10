import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class BottomNavItem(val icon: ImageVector, val label: String)

@Composable
fun BottomNavigationDemo() {
    var numberOfTabs by remember { mutableStateOf(3f) }
    var showLabels by remember { mutableStateOf(true) }

    val mockListOfTabs = mutableListOf(
        BottomNavItem(Icons.Default.Home, "Home"),
        BottomNavItem(Icons.Default.LibraryAddCheck, "Library"),
        BottomNavItem(Icons.Default.AccountCircle, "Profile"),
        BottomNavItem(Icons.Default.Settings, "Settings"),
        BottomNavItem(Icons.Default.Info, "About")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            var selectedTab by remember { mutableStateOf(0) }

            BottomAppBar() {
                repeat(numberOfTabs.toInt()) { index ->

                    val bottomNavItem = mockListOfTabs[index]
                    BottomNavigationItem(
                        icon = {
                            Icon(imageVector = bottomNavItem.icon, contentDescription = "Home")
                        },
                        alwaysShowLabel = showLabels,
                        selected = selectedTab == index,
                        label = {
                            Text(bottomNavItem.label)
                        },
                        onClick = {
                            selectedTab = index
                        }
                    )

                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Number of Tabs ")
            Slider(
                value = numberOfTabs,
                onValueChange = {
                    numberOfTabs = it
                },
                steps = 2,
                valueRange = 2f..5f,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OptionRow(
                title = "Show Labels",
                checked = showLabels,
                onCheckedChange = { showLabels = it }
            )
        }
    }
}