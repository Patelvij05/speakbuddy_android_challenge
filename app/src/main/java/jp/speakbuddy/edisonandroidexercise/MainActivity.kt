package jp.speakbuddy.edisonandroidexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.feature.fact.ui.FactContent
import jp.speakbuddy.edisonandroidexercise.feature.fact.ui.FactUiState
import jp.speakbuddy.edisonandroidexercise.navigation.EdisonNavHost
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme
import jp.speakbuddy.edisonandroidexercise.theme.components.EdisonPreview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations, and go edge-to-edge
        // This also sets up the initial system bar style based on the platform theme
        enableEdgeToEdge()

        setContent {
            EdisonAndroidExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    EdisonNavHost(
                        navController = rememberNavController(),
                    )
                }
            }
        }
    }
}

@EdisonPreview
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FactContent(
                factUiState =
                    FactUiState.Success(
                        FactModel(
                            fact =
                                "British cat owners spend roughly 550 million pounds yearly on cat food.",
                            length = 71,
                        ),
                    ),
                modifier = Modifier.padding(16.dp),
                onClick = { },
                onTopAppBarActionClick = { },
            )
        }
    }
}
