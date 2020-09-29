package pe.com.jjrodcast.challenges.backup.models

import androidx.compose.ui.graphics.Color

data class Bubble(
    val radius: Float,
    val verticalSpeed: Int,
    val horizontalSpeed: Int,
    val direction: Int,
    val color: Color,
    val initialPos: Float
)