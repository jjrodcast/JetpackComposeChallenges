package pe.com.jjrodcast.challenges.backup.composables.title

import androidx.compose.animation.transition
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.com.jjrodcast.challenges.backup.animations.propTitleOffset
import pe.com.jjrodcast.challenges.backup.animations.propTitleOpacity
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState

@Composable
fun TitleBackup(modifier: Modifier = Modifier, state: BackupAnimationState) {

    val transition = transition(
        definition = titleTransitionDefinition,
        toState = getTitleState(state)
    )

    Text(
        text = "Cloud Storage",
        modifier = modifier.padding(24.dp)
            .offset(y = transition[propTitleOffset])
            .drawOpacity(transition[propTitleOpacity]),
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
    )
}

private fun getTitleState(state: BackupAnimationState) = when (state) {
    is BackupAnimationState.Static -> TitleState.Visible
    else -> TitleState.Hidden
}