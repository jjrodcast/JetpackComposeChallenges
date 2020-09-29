package pe.com.jjrodcast.challenges.backup.composables.cloud

abstract class CloudState {
    object Static : CloudState()
    object Merge : CloudState()
    object Fill : CloudState()
}