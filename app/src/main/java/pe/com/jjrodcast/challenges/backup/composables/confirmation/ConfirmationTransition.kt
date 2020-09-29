package pe.com.jjrodcast.challenges.backup.composables.confirmation

import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp
import pe.com.jjrodcast.challenges.backup.animations.propCircleAngle
import pe.com.jjrodcast.challenges.backup.animations.propTextCompletedOffset
import pe.com.jjrodcast.challenges.backup.animations.propTextCompletedOpacity

private const val durationTime = 500

internal val confirmationTransition = transitionDefinition<ConfirmationState> {

    state(ConfirmationState.Static) {
        this[propCircleAngle] = 0f
    }
    state(ConfirmationState.Visible) {
        this[propCircleAngle] = 360f
    }

    transition(fromState = ConfirmationState.Static, toState = ConfirmationState.Visible) {
        propCircleAngle using tween(durationMillis = durationTime)
    }
}

internal val confirmationTextTransition = transitionDefinition<ConfirmationState> {

    state(ConfirmationState.Static) {
        this[propTextCompletedOffset] = 20.dp
        this[propTextCompletedOpacity] = 0f
    }
    state(ConfirmationState.Visible) {
        this[propTextCompletedOffset] = (10).dp
        this[propTextCompletedOpacity] = 1f
    }

    transition(toState = ConfirmationState.Visible) {
        propTextCompletedOffset using tween(durationMillis = durationTime, delayMillis = 10)
        propTextCompletedOpacity using tween(durationMillis = durationTime, delayMillis = 10)
    }
}