package jp.speakbuddy.edisonandroidexercise.common.domain.mapper

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import jp.speakbuddy.edisonandroidexercise.common.utils.Mapper
import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import javax.inject.Inject

class FactModelMapper
    @Inject
    constructor() : Mapper<FactResponse, FactModel> {
        override fun map(from: FactResponse): FactModel =
            FactModel(
                fact = from.fact,
                length = from.length,
            )
    }
