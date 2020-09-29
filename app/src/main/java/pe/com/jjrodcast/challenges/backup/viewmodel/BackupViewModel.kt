package pe.com.jjrodcast.challenges.backup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState
import pe.com.jjrodcast.challenges.backup.utils.io
import kotlin.random.Random

class BackupViewModel : ViewModel() {

    private val _backupState = MutableLiveData<BackupAnimationState>()
    val backupState: LiveData<BackupAnimationState>
        get() = _backupState

    // We use the Job to cancel all the background work
    private var job: Job? = null

    fun doBackup() {
        job = viewModelScope.launch {
            io { doProgress() }
        }
    }

    private suspend fun doProgress() {
        var progress = 0
        _backupState.postValue(BackupAnimationState.InProgress(progress))
        delay(600)
        while (progress < 100) {
            delay(100)
            val randValue = Random.nextInt(1, 6)
            progress = if (progress + randValue > 100) 100 else progress + randValue
            _backupState.postValue(BackupAnimationState.InProgress(progress))
        }
        delay(600)
        _backupState.postValue(BackupAnimationState.Done)
    }

    fun doCancel() {
        job?.cancel()
        viewModelScope.launch {
            _backupState.value = BackupAnimationState.Static
        }
    }

    fun doOk() = doCancel()
}