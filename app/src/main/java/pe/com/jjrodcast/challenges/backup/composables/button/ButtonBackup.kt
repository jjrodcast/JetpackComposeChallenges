package pe.com.jjrodcast.challenges.backup.composables.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState
import pe.com.jjrodcast.challenges.ui.pinkSecondary


@Composable
fun ButtonCloudBackup(
    state: BackupAnimationState,
    doBackup: () -> Unit = {},
    doCancel: () -> Unit = {}
) {
    Stack(
        modifier = Modifier.weight(1f).fillMaxWidth().padding(vertical = 48.dp, horizontal = 52.dp),
        alignment = Alignment.BottomCenter
    ) {
        when (state) {
            is BackupAnimationState.Static -> ButtonBackup(onBackup = { doBackup() })
            is BackupAnimationState.InProgress -> ButtonCancel(onCancel = { doCancel() })
            else -> Unit
        }
    }
}

@Composable
fun ButtonBackup(onBackup: () -> Unit = {}) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onBackup() },
        backgroundColor = pinkSecondary,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Create Backup",
            style = TextStyle(color = Color.White, letterSpacing = 2.sp)
        )
    }
}

@Composable
fun ButtonCancel(text: String = "Cancel", onCancel: () -> Unit = {}) {
    OutlinedButton(
        modifier = Modifier.wrapContentWidth(),
        onClick = { onCancel() },
        backgroundColor = Color.Transparent,
        border = BorderStroke(width = 1.dp, color = pinkSecondary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 36.dp),
            text = text,
            style = TextStyle(
                color = pinkSecondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonBackupPreview() {
    ButtonBackup()
}

@Preview(showBackground = true)
@Composable
fun ButtonCancelPreview() {
    ButtonCancel()
}