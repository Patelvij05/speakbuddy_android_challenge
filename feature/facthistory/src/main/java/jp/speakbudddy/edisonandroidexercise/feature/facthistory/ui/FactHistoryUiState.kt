package jp.speakbudddy.edisonandroidexercise.feature.facthistory.ui

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel

sealed interface FactHistoryUiState {
    data class Success(val fact: List<FactModel>) : FactHistoryUiState

    data class Error(val throwable: Throwable) : FactHistoryUiState

    data object Loading : FactHistoryUiState

    data object Initial : FactHistoryUiState
}
