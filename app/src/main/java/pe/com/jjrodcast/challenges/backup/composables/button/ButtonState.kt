package pe.com.jjrodcast.challenges.backup.composables.button

sealed class ButtonState {
    object Visible : ButtonState()
    object Hidden : ButtonState()
}