import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.MenuItem
import kotlin.random.Random

@Composable
fun TopAppBarDemo() {
    var appBarTitle by remember { mutableStateOf(TextFieldValue("")) }
    var navIcon by remember { mutableStateOf(false) }
    var menuItems by remember { mutableStateOf(false) }
    var numberOfMenuItems by remember { mutableStateOf(1f) }

    Column {
        TopAppBar(
            title = { Text(appBarTitle.text) },
            navigationIcon = {
                if (navIcon) {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            },
            actions = {
                if (menuItems) {
                    repeat(numberOfMenuItems.toInt()) {
                        IconButton(
                            onClick = {

                            }) {
                            Icon(
                                imageVector = listOfIcons.get(Random.nextInt(0, listOfIcons.size)),
                                contentDescription = ""
                            )
                        }
                    }
                }
            },
            elevation = 4.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = appBarTitle,
            onValueChange = {
                appBarTitle = it
            },
            label = {
                Text("App Bar Title")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OptionRow(
            title = "Navigation icon",
            checked = navIcon,
            onCheckedChange = { navIcon = it }
        )

        OptionRow(
            title = "Menu Items",
            checked = menuItems,
            onCheckedChange = { menuItems = it }
        )

        if (menuItems) {
            Text("Number of Menu Items:", modifier = Modifier.padding(top = 20.dp, start = 32.dp))

            val range = 1f..3f
            Slider(
                value = numberOfMenuItems,
                onValueChange = {
                    numberOfMenuItems = it
                },
                valueRange = range,
                steps = 1,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

    }
}

val listOfIcons =
    mutableListOf(
        Icons.Default.Add,
        Icons.Default.Check,
        Icons.Default.Done,
        Icons.Default.Edit,
        Icons.Default.Info,
        Icons.Default.Search
    )