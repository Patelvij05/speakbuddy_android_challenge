package jp.speakbuddy.edisonandroidexercise.storage

import kotlinx.coroutines.flow.Flow

interface FactHandler {
    suspend fun saveFactData(fact: FactLocal)

    suspend fun getCachedFactData(): Flow<FactLocal>
}
