package pe.com.jjrodcast.challenges.backup.composables.title

import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp
import pe.com.jjrodcast.challenges.backup.animations.propTitleOffset
import pe.com.jjrodcast.challenges.backup.animations.propTitleOpacity

private const val durationMillis = 1000

internal val titleTransitionDefinition = transitionDefinition<TitleState> {
    state(TitleState.Visible) {
        this[propTitleOffset] = 0.dp
        this[propTitleOpacity] = 1f
    }
    state(TitleState.Hidden) {
        this[propTitleOffset] = (-20).dp
        this[propTitleOpacity] = 0f
    }

    transition(fromState = TitleState.Visible, toState = TitleState.Hidden) {
        propTitleOffset using tween(durationMillis = durationMillis)
        propTitleOpacity using tween(durationMillis = durationMillis, delayMillis = 50)
    }
}