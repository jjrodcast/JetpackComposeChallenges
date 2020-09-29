package pe.com.jjrodcast.challenges.backup.composables.title

sealed class TitleState {
    object Visible : TitleState()
    object Hidden : TitleState()
}