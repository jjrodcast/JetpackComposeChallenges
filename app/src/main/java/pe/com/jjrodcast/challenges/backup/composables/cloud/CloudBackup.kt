package pe.com.jjrodcast.challenges.backup.composables.cloud

import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Radius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import pe.com.jjrodcast.challenges.backup.animations.propBubblesOffset
import pe.com.jjrodcast.challenges.backup.animations.propCloudHeight
import pe.com.jjrodcast.challenges.backup.animations.propCloudRadius
import pe.com.jjrodcast.challenges.backup.animations.propCloudWidth
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState
import pe.com.jjrodcast.challenges.backup.utils.BubbleUtils
import pe.com.jjrodcast.challenges.ui.ChallengesTheme

@Composable
fun CloudBackup(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    state: BackupAnimationState
) {

    val currentState = getCloudState(state)

    val transitionState = transition(
        definition = cloudTransitionDefinition,
        toState = currentState
    )

    Canvas(modifier.preferredSize(300.dp)) {
        val width = size.width
        val height = size.height
        val canvasSize = minOf(width, height)
        val canvasOffset = canvasSize * .2f
        val radius = canvasSize * .5f - canvasOffset
        val baseLineOffset = .24f // It just move down a little the cloud sides

        val yOffset = if (transitionState[propCloudRadius] == 0f) center.y
        else center.y - transitionState[propCloudRadius] + transitionState[propCloudHeight]

        val expandableCircle = center.copy(y = yOffset)

        val circleRadius =
            radius + transitionState[propCloudRadius] + transitionState[propCloudHeight]

        // Drawing Main Circle
        drawCircle(
            color,
            radius = circleRadius,
            center = expandableCircle
        )

        // Drawing a Rounded Rectangle for the left Cloud
        drawRoundRect(
            color,
            size = Size(
                width = width * (.65f - transitionState[propCloudWidth]),
                height = radius + radius * .3f + baseLineOffset
            ),
            radius = Radius(x = radius, y = radius),
            topLeft = Offset(
                x = width * (.05f + transitionState[propCloudWidth]),
                y = center.y - radius * .3f
            )
        )

        // Drawing a Rounded Rectangle for the right Cloud (width * .65f -> .25f)
        drawRoundRect(
            color,
            size = Size(
                width = width * (.65f - transitionState[propCloudWidth]),
                height = radius + radius * .5f + baseLineOffset
            ),
            radius = Radius(x = radius, y = radius),
            topLeft = Offset(x = width - width * .7f, y = center.y - radius * .5f)
        )

        when (state) {
            is BackupAnimationState.InProgress -> {
                createBubbles(
                    circleRadius = radius,
                    expandableRadius = circleRadius,
                    expandableCenter = expandableCircle,
                    progress = state.progress / 100f,
                    animationBubbleProgress = transitionState[propBubblesOffset]
                )
            }
            else -> Unit
        }
    }
}

private fun DrawScope.createBubbles(
    circleRadius: Float,
    expandableRadius: Float,
    expandableCenter: Offset,
    progress: Float,
    animationBubbleProgress: Float
) {
    clipPath(Path().apply {
        addRoundRect(
            RoundRect(
                left = expandableCenter.x - expandableRadius,
                top = expandableCenter.y - expandableRadius,
                right = expandableCenter.x + expandableRadius,
                bottom = expandableCenter.y + expandableRadius,
                radius = Radius(x = expandableRadius, y = expandableRadius)
            )
        )
    }) {
        bubbles.forEach {
            val verticalBottom = center.y + circleRadius + it.radius * it.initialPos
            val verticalOffset = verticalBottom -
                    (progress + animationBubbleProgress) * 1.3f * it.verticalSpeed

            val horizontalOffset = if (verticalOffset > verticalBottom) center.x
            else center.x + it.direction * it.horizontalSpeed * progress * (progress + animationBubbleProgress)

            drawCircle(
                color = it.color,
                radius = it.radius,
                center = Offset(x = horizontalOffset, y = verticalOffset)
            )
        }
    }
}


private fun getCloudState(state: BackupAnimationState) = when (state) {
    is BackupAnimationState.Static -> CloudState.Static
    is BackupAnimationState.InProgress -> CloudState.Merge
    is BackupAnimationState.Done -> CloudState.Fill
}

val bubbles = BubbleUtils.createBubbles(160)

@Preview(name = "CloudPreview", showBackground = true, showDecoration = true)
@Composable
fun CloudBackupPreview() {
    ChallengesTheme {
        CloudBackup(
            modifier = Modifier.preferredSize(400.dp),
            color = Color.Black,
            state = BackupAnimationState.Static
        )
    }
}