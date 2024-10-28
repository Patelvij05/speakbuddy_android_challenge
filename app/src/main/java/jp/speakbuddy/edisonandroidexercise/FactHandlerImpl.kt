package jp.speakbuddy.edisonandroidexercise

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jp.speakbuddy.edisonandroidexercise.storage.FactHandler
import jp.speakbuddy.edisonandroidexercise.storage.FactLocal
import jp.speakbuddy.edisonandroidexercise.storage.FactLocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.factLocalStore: DataStore<FactLocalData> by dataStore(
    fileName = "FactLocalData.pb",
    serializer = FactLocalSerializer,
)

class FactHandlerImpl
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : FactHandler {
        override suspend fun saveFactData(fact: FactLocal) {
            context.factLocalStore.updateData {
                it.toBuilder()
                    .setFact(fact.fact)
                    .setLength(fact.length)
                    .build()
            }
        }

        override suspend fun getCachedFactData(): Flow<FactLocal> =
            context.factLocalStore.data.map {
                FactLocal(it.fact, it.length)
            }
    }
