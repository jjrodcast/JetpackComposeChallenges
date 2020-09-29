package pe.com.jjrodcast.challenges.backup.composables.description

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.dp
import pe.com.jjrodcast.challenges.backup.animations.*

private const val durationTime = 700

@SuppressLint("Range")
internal val descriptionTransitionDefinition = transitionDefinition<DescriptionState> {
    state(DescriptionState.Visible) {
        this[propDescInitialOffset] = 0.dp
        this[propDescInitialOpacity] = 1f
        this[propDescTwoInitialOffset] = 0.dp
        this[propDescTwoInitialOpacity] = 1f
        this[propDescBackupOpacity] = 0f
    }
    state(DescriptionState.Hidden) {
        this[propDescInitialOffset] = (-24).dp
        this[propDescInitialOpacity] = 0f
        this[propDescTwoInitialOffset] = (-12).dp
        this[propDescTwoInitialOpacity] = 0f
        this[propDescBackupOpacity] = 1f
    }

    transition(fromState = DescriptionState.Visible, toState = DescriptionState.Hidden) {
        propDescInitialOffset using tween(durationMillis = durationTime)
        propDescInitialOpacity using tween(durationMillis = durationTime)
        propDescTwoInitialOffset using tween(durationMillis = durationTime)
        propDescTwoInitialOpacity using tween(durationMillis = durationTime)
        propDescBackupOpacity using keyframes {
            durationMillis = 300//700
            delayMillis = 200//800
            0f at 0
            .7f at 200
            1f at 500
        }

    }
}
