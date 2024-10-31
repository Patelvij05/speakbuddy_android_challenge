package jp.speakbuddy.edisonandroidexercise.feature.fact.ui

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact

sealed interface FactUiState {
    data class Success(val fact: Fact) : FactUiState

    data class Error(val throwable: Throwable) : FactUiState

    data object Loading : FactUiState
}
