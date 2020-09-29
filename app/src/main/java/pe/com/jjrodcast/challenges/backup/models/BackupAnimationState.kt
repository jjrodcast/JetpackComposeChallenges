package pe.com.jjrodcast.challenges.backup.models

sealed class BackupAnimationState {
    object Static : BackupAnimationState()
    class InProgress(var progress: Int = 0) : BackupAnimationState()
    object Done : BackupAnimationState()
}