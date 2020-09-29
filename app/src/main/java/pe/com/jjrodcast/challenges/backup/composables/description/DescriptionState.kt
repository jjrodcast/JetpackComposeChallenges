package pe.com.jjrodcast.challenges.backup.composables.description

sealed class DescriptionState {
    object Visible : DescriptionState()
    object Hidden : DescriptionState()
}