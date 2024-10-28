package jp.speakbuddy.edisonandroidexercise.feature.facthistory.data

import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel
import kotlinx.coroutines.flow.Flow

interface FactHistoryRepository {
    fun getFactHistory(): Flow<List<FactModel>>
}
