package pe.com.jjrodcast.challenges.backup.composables.confirmation

import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import pe.com.jjrodcast.challenges.backup.animations.propCircleAngle
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState
import pe.com.jjrodcast.challenges.ui.ChallengesTheme
import pe.com.jjrodcast.challenges.ui.pinkSecondary

@Composable
fun ConfirmationBackup(modifier: Modifier = Modifier, state: BackupAnimationState) {

    val transitionState = transition(
        definition = confirmationTransition,
        toState = getState(state),
        initState = ConfirmationState.Static
    )

    Canvas(modifier.preferredSize(100.dp)) {

        // Arc for the check
        drawArc(
            color = pinkSecondary,
            startAngle = 270f,
            sweepAngle = transitionState[propCircleAngle],
            useCenter = false,
            style = Stroke(4f)
        )
        // Left Line
        drawLine(
            color = pinkSecondary,
            start = Offset(x = center.x - (center.x * .35f), y = center.y),
            end = Offset(x = center.x - (center.x * .05f), y = center.y + (center.y * .25f)),
            strokeWidth = 4f
        )
        // Right Line
        drawLine(
            color = pinkSecondary,
            start = Offset(x = center.x - (center.x * .05f), y = center.y + (center.y * .25f)),
            end = Offset(x = center.x + (center.x * .4f), y = center.y - (center.y * .3f)),
            strokeWidth = 4f
        )
    }
}

private fun getState(state: BackupAnimationState) = when (state) {
    is BackupAnimationState.Done -> ConfirmationState.Visible
    else -> ConfirmationState.Static
}


@Preview(showDecoration = true)
@Composable
fun ConfirmationBackupPreview() {
    ChallengesTheme {
        ConfirmationBackup(state = BackupAnimationState.Done)
    }
}
