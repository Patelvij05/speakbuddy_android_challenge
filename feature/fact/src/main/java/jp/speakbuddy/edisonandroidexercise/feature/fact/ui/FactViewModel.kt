package jp.speakbuddy.edisonandroidexercise.feature.fact.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.speakbuddy.edisonandroidexercise.common.domain.model.Resource
import jp.speakbuddy.edisonandroidexercise.common.domain.model.ResourceError
import jp.speakbuddy.edisonandroidexercise.feature.fact.R
import jp.speakbuddy.edisonandroidexercise.feature.fact.domain.FactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun updateFact() = viewModelScope.launch {
        _factStateFlow.update { FactUiState.Loading }
        _factStateFlow.update {
            when (val result = factUseCase.invoke()) {
                is Resource.Error -> FactUiState.Error(getError(result))
                is Resource.Success -> FactUiState.Success(result.result)
            }
        }
    }

    private fun getError(loginError: Resource.Error): Int {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> R.string.txt_unauthorized_error
            ResourceError.SERVICE_UNAVAILABLE -> R.string.txt_service_unavailable
            ResourceError.UNKNOWN -> R.string.txt_unknown_error
        }
    }
}
