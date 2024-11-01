package jp.speakbuddy.edisonandroidexercise.feature.fact.domain

import jp.speakbuddy.edisonandroidexercise.common.data.model.Fact
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactEntityMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.mapper.FactModelMapper
import jp.speakbuddy.edisonandroidexercise.common.domain.model.Resource
import jp.speakbuddy.edisonandroidexercise.common.domain.toResourceError
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import jp.speakbuddy.edisonandroidexercise.database.model.asExternalModel
import jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.network.NetworkResult
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FactUseCase
@Inject
constructor(
    private val repository: FactRepository,
    private val factDao: FactDao,
    private val factEntityMapper: FactEntityMapper,
    private val factModelMapper: FactModelMapper,
) {
    suspend operator fun invoke(): Resource<Fact> {
        return when (val result = repository.getFact()) {
            is NetworkResult.Error -> {
                factDao.getLatestFactEntity()?.first()?.asExternalModel().let {
                    if (it != null) {
                        Resource.Success(it)
                    } else {
                        result.toResourceError()
                    }
                }
            }

            is NetworkResult.Success -> {
                // TODO fix me
                factDao.upsertFacts(factEntityMapper.map(factModelMapper.map(result.result)))
                Resource.Success(factModelMapper.map(result.result))
            }
        }

    }
}
