package pe.com.jjrodcast.challenges.backup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import pe.com.jjrodcast.challenges.backup.composables.BackupScreen
import pe.com.jjrodcast.challenges.backup.viewmodel.BackupViewModel
import pe.com.jjrodcast.challenges.ui.ChallengesTheme

class MainActivity : AppCompatActivity() {

    private val backupViewModel by viewModels<BackupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackupScreen(viewModel = backupViewModel)
        }
    }
}
