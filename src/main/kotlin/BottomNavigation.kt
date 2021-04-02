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
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            },
            selected = true,
            label = {
                Text("Home")
            },
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Info , contentDescription = "Exercises")
            },
            selected = true,
            label = {
                Text("Exercises")
            },
            onClick = {

            }
        )
        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Profile")
            },
            selected = true,
            label = {
                Text("Profile")
            },
            onClick = {

            }
        )
    }
}