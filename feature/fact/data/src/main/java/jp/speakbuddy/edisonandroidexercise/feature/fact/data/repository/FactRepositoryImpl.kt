package jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactEntityMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactLocalMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactModelMapper
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import jp.speakbuddy.edisonandroidexercise.database.model.asExternalModel
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.storage.FactHandler
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
        private val factHandler: FactHandler,
        private val factLocalMapper: FactLocalMapper,
        private val factEntityMapper: FactEntityMapper,
        private val factModelMapper: FactModelMapper,
        private val factMapper: FactMapper,
    ) : FactRepository {
        override fun getFact(): Flow<Fact> =
            factNetworkDataSource.fetchFact().map { response ->
                factModelMapper.map(response)
            }.onEach { result ->
                // Using proto datastore
                // factHandler.saveFact(factLocalMapper.map(result))

                // Using room database
                factDao.upsertFacts(factEntityMapper.map(result))
            }.catch { throwable ->

                // Using proto datastore
                // val fact = factHandler.getCachedFact() ?: throw throwable
                // emit(factMapper.map(fact.first()))

                // Using room database
                val fact = factDao.getLatestFactEntity() ?: throw throwable
//        if(fact is NullPointerException) {
                emit(fact.first().asExternalModel())
//        } else {
//            emit(FactModel(fact = "", length = 0))
//        }
            }
    }
