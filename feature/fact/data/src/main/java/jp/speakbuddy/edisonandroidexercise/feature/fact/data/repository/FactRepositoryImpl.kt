package jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactEntityMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactModelMapper
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import jp.speakbuddy.edisonandroidexercise.database.model.asExternalModel
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FactRepositoryImpl
    @Inject
    constructor(
        private val factNetworkDataSource: FactNetworkDataSource,
        private val factDao: FactDao,
        private val factEntityMapper: FactEntityMapper,
        private val factModelMapper: FactModelMapper,
    ) : FactRepository {
        override fun getFact(): Flow<Fact> =
            factNetworkDataSource.fetchFact().map { response ->
                factModelMapper.map(response)
            }.onEach { result ->
                factDao.upsertFacts(factEntityMapper.map(result))
            }.catch { throwable ->
                val fact = factDao.getLatestFactEntity()?.first()?.asExternalModel() ?: throw throwable
                emit(fact)
            }
    }
