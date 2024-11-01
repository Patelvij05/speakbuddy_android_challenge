package jp.speakbuddy.edisonandroidexercise.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun FullScreenCircularProgressIndicator() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@EdisonPreview
@Composable
private fun ProgressIndicatorPreview() {
    EdisonAndroidExerciseTheme {
        Surface {
            ProgressIndicator()
        }
    }
}

@EdisonPreview
@Composable
private fun FullScreenCircularProgressIndicatorPreview() {
    EdisonAndroidExerciseTheme {
        Surface {
            FullScreenCircularProgressIndicator()
        }
    }
}
