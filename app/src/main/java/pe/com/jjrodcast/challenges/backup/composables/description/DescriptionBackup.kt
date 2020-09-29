package pe.com.jjrodcast.challenges.backup.composables.description

import androidx.compose.animation.core.TransitionState
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
import pe.com.jjrodcast.challenges.backup.animations.*
import pe.com.jjrodcast.challenges.backup.models.BackupAnimationState

@Composable
fun DescriptionBackup(modifier: Modifier = Modifier, state: BackupAnimationState) {
    Stack(modifier = modifier.wrapContentHeight()) {
        val transitionState = transition(
            definition = descriptionTransitionDefinition,
            toState = getDescriptionState(state)
        )

        ContentInitial(transitionState)
        ContentBackup(transitionState, state)
    }
}

@Composable
fun ContentInitial(transitionState: TransitionState) {
    Column(
        modifier = Modifier.fillMaxWidth().zIndex(2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "last backup:",
            modifier = Modifier.offset(y = transitionState[propDescInitialOffset])
                .drawOpacity(transitionState[propDescInitialOpacity]),
            style = TextStyle(fontSize = 18.sp, letterSpacing = 1.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "22 Sep. 2020",
            modifier = Modifier.offset(y = transitionState[propDescTwoInitialOffset])
                .drawOpacity(transitionState[propDescTwoInitialOpacity]),
            style = TextStyle(fontSize = 24.sp, letterSpacing = 1.sp)
        )
    }
}

@Composable
fun ContentBackup(transitionState: TransitionState, state: BackupAnimationState) {
    when (state) {
        is BackupAnimationState.InProgress -> {
            Column(
                modifier = Modifier.fillMaxWidth().zIndex(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textModifier = Modifier.drawOpacity(transitionState[propDescBackupOpacity])

                Text(
                    text = "Uploading files",
                    modifier = textModifier,
                    style = TextStyle(fontSize = 18.sp, letterSpacing = 1.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${state.progress} %",
                    modifier = textModifier,
                    style = TextStyle(fontSize = 30.sp, letterSpacing = 1.sp)
                )
            }
        }
        else -> Unit
    }
}

private fun getDescriptionState(state: BackupAnimationState) = when (state) {
    is BackupAnimationState.Static -> DescriptionState.Visible
    else -> DescriptionState.Hidden
}


@Preview(showBackground = true, showDecoration = true)
@Composable
fun Preview() {
    DescriptionBackup(state = BackupAnimationState.InProgress(0))
}