package jp.speakbuddy.edisonandroidexercise.feature.fact.domain

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FactUseCase
    @Inject
    constructor(
        private val repository: FactRepository,
    ) {
        operator fun invoke(): Flow<Fact> = repository.getFact()
    }
