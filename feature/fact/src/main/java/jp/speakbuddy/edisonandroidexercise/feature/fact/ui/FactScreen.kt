package jp.speakbuddy.edisonandroidexercise.feature.fact.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.data.model.containCats
import jp.speakbuddy.edisonandroidexercise.common.data.model.isShowLength
import jp.speakbuddy.edisonandroidexercise.feature.fact.R
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme
import jp.speakbuddy.edisonandroidexercise.theme.components.AppBar
import jp.speakbuddy.edisonandroidexercise.theme.components.EdisonPreview
import jp.speakbuddy.edisonandroidexercise.theme.components.ProgressIndicator

@Composable
fun FactScreen(
    modifier: Modifier = Modifier,
    factViewModel: FactViewModel = viewModel(),
    onTopAppBarActionClick: () -> Unit,
) {
    val uiState by factViewModel.factStateFlow.collectAsStateWithLifecycle()
    FactContent(
        factUiState = uiState,
        modifier = modifier,
        onClick = factViewModel::updateFact,
        onTopAppBarActionClick = {
            onTopAppBarActionClick()
        },
    )
}

@Composable
fun ErrorBody(throwable: Throwable) {
    Text(
        text = stringResource(id = R.string.txt_fact_error, throwable.message.orEmpty()),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Red,
    )
}

@Composable
fun SuccessBody(fact: Fact) {
    if (fact.containCats) {
        Text(
            text = stringResource(id = R.string.txt_fact_multiple_cats),
            style = MaterialTheme.typography.titleMedium,
        )
    }
    Text(
        text = fact.fact,
        style = MaterialTheme.typography.bodyLarge,
    )
    if (fact.isShowLength) {
        Text(
            text = stringResource(id = R.string.txt_fact_length, fact.fact.length),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun FactContent(
    factUiState: FactUiState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onTopAppBarActionClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                stringResource(id = R.string.txt_fact_title),
                actionIcon = Icons.Rounded.History,
                actionIconContentDescription = "Fact History",
                onActionClick = { onTopAppBarActionClick() },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterVertically,
                ),
        ) {
            Text(
                text = stringResource(id = R.string.txt_fact_title),
                style = MaterialTheme.typography.titleLarge,
            )

            when (factUiState) {
                is FactUiState.Error -> ErrorBody(throwable = factUiState.throwable)
                FactUiState.Loading -> ProgressIndicator()
                is FactUiState.Success -> SuccessBody(fact = factUiState.fact)
            }

            Button(onClick = onClick) {
                Text(text = stringResource(id = R.string.btn_update_fact))
            }
        }
    }
}

@EdisonPreview
@Composable
private fun FactContentPreview() {
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
                onClick = { },
                onTopAppBarActionClick = { },
            )
        }
    }
}

@EdisonPreview
@Composable
private fun FactLoadingPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FactContent(
                factUiState = FactUiState.Loading,
                onClick = { },
                onTopAppBarActionClick = { },
            )
        }
    }
}
