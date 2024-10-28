package jp.speakbudddy.edisonandroidexercise.feature.facthistory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.speakbudddy.edisonandroidexercise.feature.facthistory.R
import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme
import jp.speakbuddy.edisonandroidexercise.theme.components.AppBar
import jp.speakbuddy.edisonandroidexercise.theme.components.EdisonPreview
import jp.speakbuddy.edisonandroidexercise.theme.components.FullScreenCircularProgressIndicator

@Composable
fun FactHistoryScreen(
    modifier: Modifier = Modifier,
    factHistoryViewModel: FactHistoryViewModel = viewModel(),
    onNavIconClick: () -> Unit,
) {
    val uiState by factHistoryViewModel.factHistoryStateFlow.collectAsState(FactHistoryUiState.Initial)
    FactHistoryContent(
        factHistoryUiState = uiState,
        modifier = modifier,
        onNavIconClick = { onNavIconClick() },
    )
}

@Composable
fun HistoryInitialBody(modifier: Modifier) {
    Row(
        modifier =
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier =
            Modifier
                .weight(1f)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.txt_empty_fact_history),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HistoryErrorBody(
    modifier: Modifier,
    throwable: Throwable,
) {
    Row(
        modifier =
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text =
            stringResource(
                id = R.string.txt_fact_history_error,
                throwable.message.orEmpty(),
            ),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun HistorySuccessBody(facts: List<FactModel>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(facts) { fact ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = fact.fact,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun FactHistoryContent(
    factHistoryUiState: FactHistoryUiState,
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                stringResource(id = R.string.txt_fact_history_title),
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNav = { onNavIconClick() },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =
            Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            when (factHistoryUiState) {
                is FactHistoryUiState.Error -> {
                    HistoryErrorBody(modifier = modifier, throwable = factHistoryUiState.throwable)
                }

                FactHistoryUiState.Initial -> {
                    HistoryInitialBody(modifier = modifier)
                }

                FactHistoryUiState.Loading -> FullScreenCircularProgressIndicator()
                is FactHistoryUiState.Success -> {
                    HistorySuccessBody(facts = factHistoryUiState.fact)
                }
            }
        }
    }
}

@EdisonPreview
@Composable
private fun FactHistorySuccessPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FactHistoryContent(
                factHistoryUiState =
                FactHistoryUiState.Success(
                    fact =
                    listOf(
                        FactModel(
                            fact =
                            "British cat owners spend roughly 550 million pounds yearly on cat food.",
                            length = 71,
                        ),
                        FactModel(
                            fact =
                            "British cat owners spend roughly 550 million pounds yearly on cat food.",
                            length = 71,
                        ),
                    ),
                ),
                modifier = Modifier,
                onNavIconClick = { },
            )
        }
    }
}

@EdisonPreview
@Composable
private fun FactHistoryLoadingPreview() {
    EdisonAndroidExerciseTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FactHistoryContent(
                factHistoryUiState = FactHistoryUiState.Loading,
                modifier = Modifier,
                onNavIconClick = { },
            )
        }
    }
}
