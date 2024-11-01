package jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.NetworkResult
import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import javax.inject.Inject

class FactRepositoryImpl
@Inject
constructor(
    private val factNetworkDataSource: FactNetworkDataSource,
) : FactRepository {
    override suspend fun getFact(): NetworkResult<FactResponse> {
        return factNetworkDataSource.fetchFact()
    }
}
