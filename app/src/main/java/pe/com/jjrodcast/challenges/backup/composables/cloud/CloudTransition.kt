package pe.com.jjrodcast.challenges.backup.composables.cloud

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import pe.com.jjrodcast.challenges.backup.animations.propBubblesOffset
import pe.com.jjrodcast.challenges.backup.animations.propCloudHeight
import pe.com.jjrodcast.challenges.backup.animations.propCloudRadius
import pe.com.jjrodcast.challenges.backup.animations.propCloudWidth

private const val durationTime = 600
private const val durationTimeRadius = 4500
private const val durationFill = 300

@SuppressLint("Range")
internal val cloudTransitionDefinition = transitionDefinition<CloudState> {
    state(CloudState.Static) {
        this[propCloudWidth] = .0f
        this[propCloudRadius] = 0f
        this[propCloudHeight] = 0f
        this[propBubblesOffset] = 0f
    }
    state(CloudState.Merge) {
        this[propCloudWidth] = .25f
        this[propCloudRadius] = 600f
        this[propCloudHeight] = 0f
        this[propBubblesOffset] = 100f
    }

    state(CloudState.Fill) {
        this[propCloudWidth] = .25f
        this[propCloudRadius] = 600f
        this[propCloudHeight] = 700f
        this[propBubblesOffset] = 1.8f
    }

    transition(fromState = CloudState.Static, toState = CloudState.Merge) {
        propCloudWidth using tween(durationMillis = durationTime, easing = FastOutSlowInEasing)
        propBubblesOffset using tween(durationMillis = durationTimeRadius)
        propCloudRadius using keyframes {
            durationMillis = durationTimeRadius
            delayMillis = 200
            0f to 0
            300f to 80
        }
    }

    transition(fromState = CloudState.Merge, toState = CloudState.Fill) {
        propCloudHeight using tween(durationMillis = durationFill)
    }
}