package jp.speakbuddy.edisonandroidexercise.feature.facthistory.data

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactModelEntityMapper
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FactHistoryRepositoryImpl
    @Inject
    constructor(
        private val factDao: FactDao,
        private val factModelEntityMapper: FactModelEntityMapper,
    ) : FactHistoryRepository {
        override fun getFactHistory(): Flow<List<FactModel>> =
            factDao.getFactEntities().map {
                factModelEntityMapper.map(it)
            }
    }
