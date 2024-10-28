package jp.speakbuddy.edisonandroidexercise.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.EdisonDatabase
import jp.speakbuddy.edisonandroidexercise.database.dao.FactDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesEdisonDatabase(
        @ApplicationContext context: Context,
    ): EdisonDatabase =
        Room.databaseBuilder(
            context,
            EdisonDatabase::class.java,
            "edison-database",
        ).build()

    @Provides
    fun providesFactsDao(database: EdisonDatabase): FactDao = database.topicDao()
}
