package jp.speakbuddy.edisonandroidexercise.common.domain.mapper

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.utils.Mapper
import jp.speakbuddy.edisonandroidexercise.database.model.FactEntity
import javax.inject.Inject

class FactModelEntityMapper
    @Inject
    constructor() : Mapper<List<FactEntity>, List<FactModel>> {
        override fun map(from: List<FactEntity>): List<FactModel> =
            from.flatMap { model ->
                listOf(
                    FactModel(
                        fact = model.fact,
                        length = model.length,
                    ),
                )
            }
    }
