@file:Suppress("ktlint:standard:max-line-length")

package jp.speakbuddy.edisonandroidexercise.feature.facthistory

import io.mockk.every
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.data.FactHistoryRepository
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.domain.FactHistoryUseCase
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.ui.FactHistoryUiState
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.ui.FactHistoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class FactHistoryViewModelTest {
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test update fact history success`() {
        // Given
        val expected =
            "Some Fact about cats that it is inhabiting large island in Japan where you can visit and sees them. They also have more population than human inhabitant"
        val factExpected = FactModel(fact = expected, length = 152)

        val factHistoryRepository =
            mockk<FactHistoryRepository> {
                every { getFactHistory() } returns flowOf(listOf(factExpected))
            }
        val factHistoryUseCase = FactHistoryUseCase(factHistoryRepository)
        val viewModel = FactHistoryViewModel(factHistoryUseCase)

        // When
        viewModel.updateFactHistory()

        // Then
        val factHistoryUiState = viewModel.factHistoryStateFlow.value
        assertTrue(factHistoryUiState is FactHistoryUiState.Success && factHistoryUiState.fact.isNotEmpty())
        assertTrue(factHistoryUiState is FactHistoryUiState.Success && factHistoryUiState.fact[0].fact == expected)
    }

    @Test
    fun `test update fact history error`() {
        // Given
        val error = RuntimeException()
        val factHistoryRepository =
            mockk<FactHistoryRepository> {
                every { getFactHistory() } returns flow { throw error }
            }

        val factHistoryUseCase = FactHistoryUseCase(factHistoryRepository)
        val viewModel = FactHistoryViewModel(factHistoryUseCase)

        // When
        viewModel.updateFactHistory()

        // Then
        val factHistoryUiState = viewModel.factHistoryStateFlow.value
        assertTrue(factHistoryUiState is FactHistoryUiState.Error && factHistoryUiState.throwable == error)
    }

    @Test
    fun `test update fact history loading`() {
        // Given
        val factHistoryRepository =
            mockk<FactHistoryRepository> {
                every { getFactHistory() } returns flow { }
            }
        val factHistoryUseCase = FactHistoryUseCase(factHistoryRepository)
        val viewModel = FactHistoryViewModel(factHistoryUseCase)

        // When
        viewModel.updateFactHistory()

        // Then
        val factHistoryUiState = viewModel.factHistoryStateFlow.value
        assertTrue(factHistoryUiState is FactHistoryUiState.Loading)
    }
}
