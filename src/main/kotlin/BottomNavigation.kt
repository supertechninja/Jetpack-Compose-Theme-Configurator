import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigation() {
    BottomAppBar {
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Home)
            },
            selected = true,
            label = {
                Text("Home")
            },
            alwaysShowLabels = true,
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Info)
            },
            selected = true,
            label = {
                Text("Exercises")
            },
            alwaysShowLabels = true,
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Delete)
            },
            selected = true,
            label = {
                Text("Profile")
            },
            alwaysShowLabels = true,
            onClick = {

            }
        )
    }
}