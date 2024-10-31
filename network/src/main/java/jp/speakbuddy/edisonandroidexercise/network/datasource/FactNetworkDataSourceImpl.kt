package jp.speakbuddy.edisonandroidexercise.network.datasource

import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.FactService
import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FactNetworkDataSourceImpl
    @Inject
    constructor(private val factService: FactService) :
    FactNetworkDataSource {
        override fun fetchFact(): Flow<FactResponse> = flow { emit(factService.getFact()) }
    }
