import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue

@ExperimentalAnimationApi
@Composable
fun ThemeConfig(showThemeSettings: MutableState<Boolean>) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Theme Settings", color = Color.White)
            }, backgroundColor = Color.Black,
                navigationIcon = {
                    if (showThemeSettings.value) {
                        IconButton(onClick = {
                            showThemeSettings.value = false
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", tint = Color.White)
                        }
                    }
                })

        },
        content = {
            var showDialog by remember { mutableStateOf(false) }
            var colorState = rememberColorState()

            Column(modifier = Modifier.fillMaxSize()) {
                val colorPrimary = Color.Blue
                Row(
                    modifier = Modifier.padding(16.dp).wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(48.dp).clickable(
                            onClick = {
                                colorState = ColorState(color = colorPrimary, updateColor = {
                                    showDialog = false
                                })
                                showDialog = true
                            }),
                        shape = CircleShape,
                        color = MaterialTheme.colors.primary
                    ) {}

                    Text("Primary Color")
                }
            }

            AnimatedVisibility(showDialog) {
                ColorPicker(colorState)
            }
        })
}
