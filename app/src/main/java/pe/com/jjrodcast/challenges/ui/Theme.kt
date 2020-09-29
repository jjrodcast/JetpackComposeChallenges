package pe.com.jjrodcast.challenges.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val ColorPalette = lightColors(
    primary = pinkPrimary,
    primaryVariant = pinkPrimaryVariant,
    secondary = pinkSecondary
)

@Composable
fun ChallengesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}