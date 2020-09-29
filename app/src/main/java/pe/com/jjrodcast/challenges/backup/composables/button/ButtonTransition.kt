package pe.com.jjrodcast.challenges.backup.composables.button

import androidx.compose.animation.core.transitionDefinition

internal val buttonBackupTransition = transitionDefinition<ButtonState> {
    state(ButtonState.Visible) {}
    state(ButtonState.Hidden) {}

    transition(fromState = ButtonState.Visible, toState = ButtonState.Hidden) { }
}