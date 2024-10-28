package jp.speakbuddy.edisonandroidexercise.common.domain.mapper

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.utils.Mapper
import jp.speakbuddy.edisonandroidexercise.storage.FactLocal
import javax.inject.Inject

class FactLocalMapper
    @Inject
    constructor() : Mapper<FactModel, FactLocal> {
        override fun map(from: FactModel): FactLocal =
            FactLocal(
                fact = from.fact,
                length = from.length,
            )
    }
