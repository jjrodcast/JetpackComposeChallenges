package pe.com.jjrodcast.challenges.backup.composables.confirmation

sealed class ConfirmationState {
    object Static : ConfirmationState()
    object Visible : ConfirmationState()
}