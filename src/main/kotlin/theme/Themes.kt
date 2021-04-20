package theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Yellow600 = Color(0xFFF5CF1B)
val Yellow700 = Color(0xFFF3B711)
val Yellow800 = Color(0xFFF29F05)

val Blue500 = Color(0xFF0540F2)
val Blue800 = Color(0xFF001CCF)

private val Red800 = Color(0xFFD00036)

private val DefaultThemePalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue800,
    onPrimary = Color.White,
    secondary = Yellow700,
    secondaryVariant = Yellow800,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    error = Red800,
    onError = Color.White
)

@Composable
fun ComposeComponentsTheme(
    colors: Colors = DefaultThemePalette,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors =  colors,
        content = content,
        shapes = shapes
    )
}
