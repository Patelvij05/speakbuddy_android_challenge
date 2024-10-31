package jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    fun getFact(): Flow<Fact>
}
