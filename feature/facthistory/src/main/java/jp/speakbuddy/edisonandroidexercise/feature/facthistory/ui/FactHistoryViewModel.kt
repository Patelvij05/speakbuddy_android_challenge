package jp.speakbuddy.edisonandroidexercise.feature.facthistory.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.domain.FactHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class
FactHistoryViewModel
    @Inject
    constructor(
        private val factHistoryUseCase: FactHistoryUseCase,
    ) : ViewModel() {
        private val _factHistoryStateFlow: MutableStateFlow<FactHistoryUiState> =
            MutableStateFlow(FactHistoryUiState.Initial)
        val factHistoryStateFlow: StateFlow<FactHistoryUiState> = _factHistoryStateFlow.asStateFlow()

        init {
            updateFactHistory()
        }

        fun updateFactHistory() {
            _factHistoryStateFlow.update { FactHistoryUiState.Loading }
            factHistoryUseCase.invoke().onEach { fact ->
                _factHistoryStateFlow.update {
                    if (fact.isNotEmpty()) {
                        FactHistoryUiState.Success(fact)
                    } else {
                        FactHistoryUiState.Initial
                    }
                }
            }.catch { throwable ->
                _factHistoryStateFlow.update { FactHistoryUiState.Error(throwable) }
            }.launchIn(viewModelScope)
        }
    }
