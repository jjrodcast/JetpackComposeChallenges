package pe.com.jjrodcast.challenges.backup.composables

import androidx.compose.animation.transition
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.ui.tooling.preview.Preview
import pe.com.jjrodcast.challenges.backup.animations.propTextCompletedOffset
import pe.com.jjrodcast.challenges.backup.animations.propTextCompletedOpacity
import pe.com.jjrodcast.challenges.backup.composables.button.ButtonCancel
import pe.com.jjrodcast.challenges.backup.composables.confirmation.ConfirmationBackup
import pe.com.jjrodcast.challenges.backup.composables.confirmation.ConfirmationState
import pe.com.jjrodcast.challenges.backup.composables.confirmation.confirmationTextTransition
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState

@Composable
fun BackupCompletedScreen(
    state: BackupAnimationState,
    onOk: () -> Unit = {}
) {
    when (state) {
        is BackupAnimationState.Done -> {
            val transition = transition(
                definition = confirmationTextTransition,
                initState = ConfirmationState.Static,
                toState = ConfirmationState.Visible
            )

            val textStyle = TextStyle(fontSize = 18.sp)

            Column(
                modifier = Modifier.fillMaxSize().zIndex(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConfirmationBackup(modifier = Modifier.padding(top = 120.dp, bottom = 32.dp), state)
                Spacer(modifier = Modifier.preferredHeight(32.dp).zIndex(3f))
                Text(
                    text = "data has successfully",
                    modifier = Modifier
                        .offset(y = transition[propTextCompletedOffset])
                        .drawOpacity(transition[propTextCompletedOpacity]),
                    letterSpacing = 1.sp,
                    style = textStyle
                )
                Text(
                    text = "uploaded",
                    modifier =  Modifier
                        .offset(y = transition[propTextCompletedOffset])
                        .drawOpacity(transition[propTextCompletedOpacity]),
                    letterSpacing = 1.sp,
                    style = textStyle
                )
                Stack(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                        .padding(vertical = 48.dp, horizontal = 52.dp),
                    alignment = Alignment.BottomCenter
                ) {
                    ButtonCancel(text = "OK", onCancel = { onOk() })
                }
            }
        }
        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun BackupCompetedScreenPreview() {
    BackupCompletedScreen(BackupAnimationState.Done)
}
