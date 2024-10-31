package jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.network.NetworkResult
import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse

interface FactRepository {
    suspend fun getFact(): NetworkResult<FactResponse>
}
