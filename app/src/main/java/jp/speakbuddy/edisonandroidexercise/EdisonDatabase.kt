package jp.speakbuddy.edisonandroidexercise

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import jp.speakbuddy.edisonandroidexercise.database.model.FactEntity

@Database(
    entities = [
        FactEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
internal abstract class EdisonDatabase : RoomDatabase() {
    abstract fun topicDao(): FactDao
}
