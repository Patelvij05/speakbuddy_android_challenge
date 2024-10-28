package jp.speakbuddy.edisonandroidexercise.common.domain.mapper

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.utils.Mapper
import jp.speakbuddy.edisonandroidexercise.storage.FactLocal
import javax.inject.Inject

class FactMapper
    @Inject
    constructor() : Mapper<FactLocal, FactModel> {
        override fun map(from: FactLocal): FactModel =
            FactModel(
                fact = from.fact,
                length = from.length,
            )
    }
