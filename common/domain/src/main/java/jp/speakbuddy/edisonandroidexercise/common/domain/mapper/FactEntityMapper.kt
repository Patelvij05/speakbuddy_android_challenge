package jp.speakbuddy.edisonandroidexercise.common.domain.mapper

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.utils.Mapper
import jp.speakbuddy.edisonandroidexercise.database.model.FactEntity
import javax.inject.Inject

class FactEntityMapper
    @Inject
    constructor() : Mapper<FactModel, FactEntity> {
        override fun map(from: FactModel): FactEntity =
            FactEntity(
                fact = from.fact,
                length = from.length,
            )
    }
