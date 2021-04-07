package theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Yellow400 = Color(0xFFF6E547)
val Yellow600 = Color(0xFFF5CF1B)
val Yellow700 = Color(0xFFF3B711)
val Yellow800 = Color(0xFFF29F05)

val Blue200 = Color(0xFF059EDC)
val Blue400 = Color(0xFF4860F7)
val Blue500 = Color(0xFF0540F2)
val Blue800 = Color(0xFF001CCF)

private val Red300 = Color(0xFFEA6D7E)
private val Red800 = Color(0xFFD00036)

private val SkyBlue = Color(0xFF059EDC)

private val TheNinjaMethodLightPalette = lightColors(
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
    colors: Colors = TheNinjaMethodLightPalette,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors =  colors,
        content = content,
        shapes = JetchatShapes
    )
}
