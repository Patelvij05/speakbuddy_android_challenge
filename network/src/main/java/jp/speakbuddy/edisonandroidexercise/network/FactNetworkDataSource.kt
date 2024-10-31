package jp.speakbuddy.edisonandroidexercise.network

import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import kotlinx.coroutines.flow.Flow

interface FactNetworkDataSource {
    fun fetchFact(): Flow<FactResponse>
}
