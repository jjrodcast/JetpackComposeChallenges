package pe.com.jjrodcast.challenges.backup.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import pe.com.jjrodcast.challenges.backup.composables.button.ButtonCloudBackup
import pe.com.jjrodcast.challenges.backup.composables.cloud.CloudBackup
import pe.com.jjrodcast.challenges.backup.composables.description.DescriptionBackup
import pe.com.jjrodcast.challenges.backup.composables.title.TitleBackup
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState
import pe.com.jjrodcast.challenges.backup.viewmodel.BackupViewModel
import pe.com.jjrodcast.challenges.ui.ChallengesTheme

@Composable
fun BackupScreen(viewModel: BackupViewModel) {
    val state = viewModel.backupState.observeAsState(initial = BackupAnimationState.Static)
    ChallengesTheme {
        Stack(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary)
            ) {
                TitleBackup(state = state.value)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CloudBackup(
                        modifier = Modifier.preferredSize(330.dp).zIndex(3f),
                        color = Color.White,
                        state = state.value
                    )
                }
                DescriptionBackup(
                    modifier = Modifier.zIndex(1f),
                    state = state.value
                )
                ButtonCloudBackup(
                    state = state.value,
                    doBackup = { viewModel.doBackup() },
                    doCancel = { viewModel.doCancel() }
                )
            }
            BackupCompletedScreen(
                state = state.value,
                onOk = { viewModel.doOk() }
            )
        }
    }
}