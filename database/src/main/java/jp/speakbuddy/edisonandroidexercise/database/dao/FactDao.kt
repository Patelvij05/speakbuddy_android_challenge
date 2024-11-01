package jp.speakbuddy.edisonandroidexercise.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import jp.speakbuddy.edisonandroidexercise.database.model.FactEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [FactEntity] access
 */
@Dao
interface FactDao {
    /**
     * Get all facts from the db
     */
    @Query(value = "SELECT * FROM facts")
    fun getFactEntities(): Flow<List<FactEntity>>

    /**
     * Get latest facts from the db
     */
    @Query(value = "SELECT * From facts ORDER BY id DESC LIMIT 1")
    fun getLatestFactEntity(): Flow<FactEntity>?

    /**
     * Inserts or updates [entities] in the db under the specified primary keys
     */
    @Upsert
    suspend fun upsertFacts(entities: FactEntity)
}
