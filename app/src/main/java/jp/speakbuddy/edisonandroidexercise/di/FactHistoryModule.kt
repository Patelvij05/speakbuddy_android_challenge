package jp.speakbuddy.edisonandroidexercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.data.FactHistoryRepository
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.data.FactHistoryRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object FactHistoryModule {
    @Provides
    fun provideFactHistoryRepository(impl: FactHistoryRepositoryImpl): FactHistoryRepository = impl
}
