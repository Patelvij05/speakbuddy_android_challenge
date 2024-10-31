package jp.speakbuddy.edisonandroidexercise.feature.fact.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.feature.fact.domain.FactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FactViewModel
    @Inject
    constructor(
        private val factUseCase: FactUseCase,
    ) : ViewModel() {
        private val _factStateFlow: MutableStateFlow<FactUiState> =
            MutableStateFlow(FactUiState.Loading)
        val factStateFlow: StateFlow<FactUiState> = _factStateFlow.asStateFlow()

        init {
            updateFact()
        }

        fun updateFact() {
            _factStateFlow.update { FactUiState.Loading }
            factUseCase.invoke().onEach { fact ->
                _factStateFlow.update { FactUiState.Success(fact) }
            }.catch { throwable ->
                _factStateFlow.update { FactUiState.Error(throwable) }
            }.launchIn(viewModelScope)
        }
    }
