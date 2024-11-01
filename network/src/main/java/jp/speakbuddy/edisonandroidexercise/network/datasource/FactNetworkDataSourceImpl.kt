package jp.speakbuddy.edisonandroidexercise.network.datasource

import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.NetworkResult
import jp.speakbuddy.edisonandroidexercise.network.http.RequestHandler
import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import javax.inject.Inject

private const val FACT = "fact"

class FactNetworkDataSourceImpl @Inject constructor(private val requestHandler: RequestHandler) :
    FactNetworkDataSource {
    override suspend fun fetchFact(): NetworkResult<FactResponse> = requestHandler.get(
        urlPathSegments = listOf(FACT),
    )
}