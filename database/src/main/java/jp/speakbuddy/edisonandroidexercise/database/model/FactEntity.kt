package jp.speakbuddy.edisonandroidexercise.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.speakbuddy.edisonandroidexercise.common.data.model.FactModel

/**
 * Defines a fact.
 */
@Entity(
    tableName = "facts",
)
data class FactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fact: String,
    val length: Int,
)

fun FactEntity.asExternalModel() =
    FactModel(
        fact = fact,
        length = length,
    )
