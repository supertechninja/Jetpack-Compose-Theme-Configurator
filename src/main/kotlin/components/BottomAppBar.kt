package components

import OptionRow
import Title
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBarDemo() {
    var cutOutEnabled by remember { mutableStateOf(false) }
    var cutoutShape by remember { mutableStateOf(CutOutShapes.None) }
    var fabPosition by remember { mutableStateOf(FabPosition.End) }

    System.out.println(cutoutShape.name)
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                BottomAppBar(cutoutShape = if (cutOutEnabled) getShape(cutoutShape) else null) {

                }
            },
            floatingActionButtonPosition = fabPosition,
            content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    Title("Options")

                    OptionRow(title = "Enable FAB", checked = cutoutShape != CutOutShapes.None, onCheckedChange = {
                        cutoutShape = CutOutShapes.Rectangle
                    })

                    OptionRow(title = "Enable Cutout", checked = cutOutEnabled, onCheckedChange = {
                        cutOutEnabled = !cutOutEnabled
                    })

                    if (cutOutEnabled || cutoutShape != CutOutShapes.None) {
                        Title("Shapes")

                        CutOutShapes.values().map { it.name }.forEach { text ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (text == cutoutShape.name),
                                        onClick = {
                                            cutoutShape =
                                                CutOutShapes.valueOf(text)
                                        }
                                    )
                                    .padding(horizontal = 16.dp),
                            ) {
                                RadioButton(
                                    selected = (text == cutoutShape.name),
                                    onClick = {
                                        cutoutShape =
                                            CutOutShapes.valueOf(text)
                                    },
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.body1.merge(),
                                    modifier = Modifier.padding(start = 16.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }

                        if(cutoutShape != CutOutShapes.None) {
                            Spacer(modifier = Modifier.height(16.dp))
                            OptionRow(
                                title = "Center Fab",
                                checked = fabPosition == FabPosition.Center,
                                onCheckedChange = {
                                    if (fabPosition != FabPosition.Center) {
                                        fabPosition = FabPosition.Center
                                    } else {
                                        fabPosition = FabPosition.End
                                    }
                                })
                        }

                    }
                }
            },
            isFloatingActionButtonDocked = cutOutEnabled,
            floatingActionButton = getFloatingActionButton(cutoutShape),
        )


    }
}

fun getShape(cutOutShapes: CutOutShapes) = when (cutOutShapes) {
    CutOutShapes.Rectangle -> RectangleShape
    CutOutShapes.Circle -> CircleShape
    CutOutShapes.CutCorner -> CutCornerShape(50)
    else -> null
}

fun getFloatingActionButton(cutOutShapes: CutOutShapes): (@Composable () -> Unit) {
    when (cutOutShapes) {
        CutOutShapes.Rectangle -> {
            val composeable = @Composable() {
                FloatingActionButton(shape = RectangleShape, onClick = {}) {}
            }

            return composeable
        }
        CutOutShapes.Circle -> {
            val composeable = @Composable() {
                FloatingActionButton(shape = CircleShape, onClick = {}) {}
            }
            return composeable
        }
        CutOutShapes.CutCorner -> {
            val composeable = @Composable() {
                FloatingActionButton(shape = CutCornerShape(50), onClick = {}) {}
            }
            return composeable
        }
        CutOutShapes.None -> {
            return (@Composable() {})
        }
    }
}

enum class CutOutShapes() {
    Rectangle,
    Circle,
    CutCorner,
    None
}