package jp.speakbuddy.edisonandroidexercise.feature.facthistory.domain

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.data.FactHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactHistoryUseCase
    @Inject
    constructor(
        private val repository: FactHistoryRepository,
    ) {
        operator fun invoke(): Flow<List<FactModel>> = repository.getFactHistory()
    }
