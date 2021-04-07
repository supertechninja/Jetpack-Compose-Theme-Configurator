import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.math.roundToInt

@Composable
fun ColorPicker(colorState: ColorState) {
    var redValue by remember { mutableStateOf(colorState.color.red) }
    var greenValue by remember { mutableStateOf(colorState.color.green) }
    var blueValue by remember { mutableStateOf(colorState.color.blue) }
    var alphaValue by remember { mutableStateOf(colorState.color.alpha) }
    var colorInput by remember { mutableStateOf("#${colorState.color.toHexString()}") }
    Dialog(
        onDismissRequest =
        { }, properties = DialogProperties(size = IntSize(400, 525))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .background(color = androidx.compose.ui.graphics.Color.White).padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Color Picker", style = MaterialTheme.typography.h5, color = Color.Black)
            }
            val newColor = Color(redValue, greenValue, blueValue, alphaValue)
            ColorSlider(
                sliderValue = redValue,
                onValueChange = {
                    redValue = it
                    colorInput = "#${newColor.toHexString()}"
                }, trackColor = Color.Red,
                text = "${redValue.toARGB()}"
            )
            ColorSlider(
                sliderValue = greenValue,
                onValueChange = {
                    greenValue = it
                    colorInput = "#${newColor.toHexString()}"
                }, trackColor = Color.Green,
                text = "${greenValue.toARGB()}"
            )
            ColorSlider(sliderValue = blueValue, onValueChange = {
                blueValue = it
                colorInput = "#${newColor.toHexString()}"
            }, trackColor = Color.Blue, text = "${blueValue.toARGB()}")
            ColorSlider(
                sliderValue = alphaValue,
                onValueChange = {
                    alphaValue = it
                    colorInput = "#${newColor.toHexString()}"
                },
                trackColor = lerp(Color.Transparent, Color.Black, alphaValue),
                text = "${alphaValue.times(100).roundToInt()}%"
            )
            Surface(modifier = Modifier.width(200.dp).height(100.dp), color = newColor) {
                Column() {
                    Text(
                        text = "rgb(${redValue.toARGB()}, ${greenValue.toARGB()}, ${blueValue.toARGB()})",
                        modifier = Modifier.padding(8.dp),
                        color = if (newColor.luminance() < 0.5 && newColor.alpha.times(100) > 50) Color.White else Color.Black
                    )
                    Text(
                        text =
                        "#${newColor.toHexString()}",
                        modifier = Modifier.padding(8.dp),
                        color = if (newColor.luminance() < 0.5 && newColor.alpha.times(100) > 50) Color.White else Color.Black
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = {
                    colorState.updateColor(
                        newColor
                    )
                }, modifier = Modifier.padding(8.dp)) { Text(text = "Save") }
            }
        }
    }
}

@Composable
fun ColorSlider(sliderValue: Float, onValueChange: (Float) -> Unit, trackColor: Color, text: String) {
    Box(
        modifier = Modifier.padding(12.dp).fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = sliderValue,
                valueRange = 0f..1f,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(8 / 10f),
                colors = SliderDefaults.colors(activeTrackColor = trackColor)
            )
            Text(text = text, color = Color.Black)
        }
    }
}

fun Float.round() = "%.3f".format(this)
fun Color.toHexString(): String = Integer.toHexString(this.toArgb())
fun Float.toARGB() = this.times(255).roundToInt()