import androidx.compose.animation.*
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.desktop.Window
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import components.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import theme.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.lang.StringBuilder

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun main() = Window(title = "Material Theme Config", resizable = true) {
    var selectedComponent by remember { mutableStateOf(Components.NONE) }
    var backdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()
    var showThemeSettings by remember { mutableStateOf(backdropScaffoldState.isRevealed) }

    var showDialog by remember { mutableStateOf(false) }
    var showDrawer by remember { mutableStateOf(true) }
    var showExportTheme by remember { mutableStateOf(false) }

    var primaryColor by remember { mutableStateOf(Blue500) }
    val primaryColorState = rememberColorState(color = primaryColor, updateColor = {
        primaryColor = it
        showDialog = false
    })

    var primaryAccentColor by remember { mutableStateOf(Blue800) }
    val primaryAccentColorState = rememberColorState(color = primaryAccentColor, updateColor = {
        primaryAccentColor = it
        showDialog = false
    })

    var secondaryColor by remember { mutableStateOf(Yellow600) }
    val secondaryColorState = rememberColorState(color = secondaryColor, updateColor = {
        secondaryColor = it
        showDialog = false
    })

    var secondaryAccentColor by remember { mutableStateOf(Yellow800) }
    val secondaryAccentColorState = rememberColorState(color = secondaryAccentColor, updateColor = {
        secondaryAccentColor = it
        showDialog = false
    })

    val listOfColorStates =
        listOf(
            Pair("Primary", primaryColorState),
            Pair("Primary Variant", primaryAccentColorState),
            Pair("Secondary", secondaryColorState),
            Pair("Secondary Variant", secondaryAccentColorState)
        )

    var colorStateToUpdate = rememberColorState {}

    var title = if (showThemeSettings) "Theme" else "Compose Components"

    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    ComposeComponentsTheme(
        if (isDarkThemeEnabled) {
            getDarkColorPalette(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                primaryAccentColor = primaryAccentColor,
                secondaryAccentColor = secondaryAccentColor
            )
        } else {
            getLightColorPalette(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                primaryAccentColor = primaryAccentColor,
                secondaryAccentColor = secondaryAccentColor
            )
        }
    ) {
        BackdropScaffold(
            scaffoldState = backdropScaffoldState,
            frontLayerShape = RoundedCornerShape(20.dp),
            frontLayerScrimColor = Color.Transparent,
            backLayerBackgroundColor = Color.White,
            backLayerContentColor = MaterialTheme.colors.primary.copy(alpha = .4f),
            appBar = {
                TopAppBar(
                    title = {
                        Text(text = title, color = Color.White)
                    },
                    elevation = 8.dp,
                    navigationIcon = {
                        IconButton(onClick = {
                            showDrawer = !showDrawer
                        }) {
                            Icon(imageVector = Icons.Default.Menu, tint = Color.White, contentDescription = "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            showThemeSettings = !showThemeSettings
                            if (showThemeSettings) {
                                coroutineScope.launch {
                                    backdropScaffoldState.reveal()
                                }
                            } else {
                                coroutineScope.launch {
                                    backdropScaffoldState.conceal()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Default.ColorLens, tint = Color.White, contentDescription = "")
                        }

                        val themeMode = if (isDarkThemeEnabled) Icons.Default.LightMode else Icons.Default.DarkMode
                        IconButton(onClick = {
                            isDarkThemeEnabled = !isDarkThemeEnabled
                        }) {
                            Icon(imageVector = themeMode, tint = Color.White, contentDescription = "")
                        }
                    }
                )

            },
            frontLayerElevation = 16.dp,
            frontLayerContent = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row {
                        AnimatedVisibility(showDrawer) {
                            Column(
                                modifier = Modifier.fillMaxHeight().width(220.dp)
                                    .background(MaterialTheme.colors.surface)
                                    .padding(bottom = 8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                LazyColumn() {
                                    items(Components.values().toList()) {
                                        val backgroundColor =
                                            if (it == selectedComponent) {
                                                Color.LightGray
                                            } else MaterialTheme.colors.surface
                                        ListItem(
                                            modifier = Modifier.clickable(onClick = {
                                                selectedComponent = it
                                            }).background(color = backgroundColor),
                                            text = {
                                                Text(it.componentName, color = MaterialTheme.colors.onSurface)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        if (showDrawer) {
                            Divider(
                                modifier = Modifier.width(1.dp).fillMaxHeight(),
                                color = MaterialTheme.colors.onBackground
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))

                            when (selectedComponent) {
                                Components.NONE -> {
                                    AllComponentsDemo()
                                }
                                Components.TOP_APP_BAR -> {
                                    TopAppBarDemo()
                                }
                                Components.BUTTONS -> {
                                    Buttons()
                                }
                                Components.TEXTFIELDS -> {
                                    TextFields()
                                }
                                Components.LISTITEMS -> {
                                    ListItems()
                                }
                                Components.TABS -> {
                                    TabsDemo()
                                }
                                Components.BOTTOM_NAV -> {
                                    BottomNavigationDemo()
                                }
                                Components.FAB -> {
                                    FloatingActionButtonDemo()
                                }
                                Components.DIALOGS -> {
                                    DialogDemo()
                                }
                                Components.BOTTOM_APP_BAR -> {
                                    BottomAppBarDemo()
                                }
                            }
                        }
                    }

                    AnimatedVisibility(showDialog) {
                        ColorPicker(colorStateToUpdate)
                    }

                    AnimatedVisibility(showExportTheme) {
                        Dialog(
                            onDismissRequest = {
                                showExportTheme = !showExportTheme
                            }, properties =
                            DialogProperties(
                                title = "Export Theme",
                                size = IntSize(400, 425)
                            )
                        ) {
                            Column(modifier = Modifier.wrapContentHeight()) {
                                val themeCode = exportTheme(
                                    primaryColor,
                                    primaryAccentColor,
                                    secondaryColor,
                                    secondaryAccentColor
                                ).toString()

                                var isTextCopied by remember { mutableStateOf(false) }
                                val buttonText = if (isTextCopied) "COPIED to Clipboard" else "Copy to Clipboard"

                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.End,
                                ) {
                                    TextButton(
                                        onClick = {
                                            Toolkit.getDefaultToolkit()
                                                .systemClipboard
                                                .setContents(
                                                    StringSelection(themeCode),
                                                    null
                                                )
                                            isTextCopied = !isTextCopied
                                        }
                                    ) {
                                        Text(
                                            buttonText,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                Surface(
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(10),
                                    elevation = 8.dp,
                                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = exportTheme(
                                            primaryColor,
                                            primaryAccentColor,
                                            secondaryColor,
                                            secondaryAccentColor
                                        ).toString(),
                                        modifier = Modifier.padding(16.dp),
                                        color = Color.White,
                                        style = MaterialTheme.typography.caption
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(
                                        onClick = {
                                            showExportTheme = !showExportTheme
                                        }
                                    ) {
                                        Text("OK")
                                    }
                                }
                            }
                        }
                    }
                }
            },
            backLayerContent = {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(color = MaterialTheme.colors.surface),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = {
                            showExportTheme = !showExportTheme
                        }, modifier = Modifier.padding(top = 8.dp)) {
                            Text("Export Theme")
                        }
                    }

                    LazyVerticalGrid(
                        cells = GridCells.Fixed(4),
                        modifier = Modifier.background(color = MaterialTheme.colors.surface)
                    ) {
                        items(listOfColorStates) {
                            Box(
                                modifier = Modifier.padding(16.dp).wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Surface(
                                        modifier = Modifier.size(48.dp).clickable(
                                            onClick = {
                                                colorStateToUpdate = it.second
                                                showDialog = true
                                            }),
                                        shape = CircleShape,
                                        elevation = 8.dp,
                                        border = BorderStroke(2.dp, Color.White),
                                        color = it.second.color
                                    ) {}

                                    Text(
                                        it.first,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colors.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

        )
    }
}

class ColorState(
    val color: Color,
    val updateColor: (Color) -> Unit
)

@Composable
fun rememberColorState(
    color: Color = Color.Yellow, updateColor: (Color) -> Unit = {}
) = ColorState(
    color = color,
    updateColor = updateColor
)

private enum class Components(val componentName: String) {
    NONE("All Components"),
    TOP_APP_BAR("Top App Bar"),
    BOTTOM_APP_BAR("Bottom App Bar"),
    BUTTONS("Buttons"),
    TEXTFIELDS("TextFields"),
    BOTTOM_NAV("Bottom Navigation"),
    TABS("Tabs"),
    LISTITEMS("ListItems"),
    FAB("Floating Action Buttons"),
    DIALOGS("Dialogs"),
//    CARDS("Cards"),
//    MENUS("Menus")
}

fun getLightColorPalette(
    primaryColor: Color,
    secondaryColor: Color,
    primaryAccentColor: Color,
    secondaryAccentColor: Color
) = lightColors(
    primary = primaryColor,
    primaryVariant = primaryAccentColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryAccentColor,
)

fun getDarkColorPalette(
    primaryColor: Color,
    secondaryColor: Color,
    primaryAccentColor: Color,
    secondaryAccentColor: Color
) = darkColors(
    primary = primaryColor,
    primaryVariant = primaryAccentColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryAccentColor,
)

fun exportTheme(
    primaryColor: Color,
    primaryAccentColor: Color,
    secondaryColor: Color,
    secondaryAccentColor: Color
): StringBuilder {
    val stringBuilder = StringBuilder()

    stringBuilder.append("val primaryColor = Color(0x${primaryColor.toHexString()})\n")
    stringBuilder.append("val primaryVariantColor = Color(0x${primaryAccentColor.toHexString()})\n")
    stringBuilder.append("val secondaryColor = Color(0x${secondaryColor.toHexString()})\n")
    stringBuilder.append("val secondaryColorVariant = Color(0x${secondaryAccentColor.toHexString()})\n\n")

    stringBuilder.append("val lightColorPalette = lightColors(\n")
    stringBuilder.append("    primary = primaryColor,\n")
    stringBuilder.append("    primaryVariant = primaryVariantColor,\n")
    stringBuilder.append("    secondary = secondaryColor,\n")
    stringBuilder.append("    secondaryVariant = secondaryColorVariant\n)\n\n")

    stringBuilder.append("@Composable\n")
    stringBuilder.append("fun YourThemeName(\n")
    stringBuilder.append("    colors: Colors = lightColorPalette,\n")
    stringBuilder.append("    content: @Composable () -> Unit\n")
    stringBuilder.append(") {\n")
    stringBuilder.append("    MaterialTheme(\n")
    stringBuilder.append("    colors = colors,\n")
    stringBuilder.append("    content = content,\n")
    stringBuilder.append("    shapes = shapes\n")
    stringBuilder.append("    )\n")
    stringBuilder.append("}\n")

    return stringBuilder
}