package jp.speakbuddy.edisonandroidexercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository.FactRepository
import jp.speakbuddy.edisonandroidexercise.feature.fact.data.repository.FactRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object FactModule {
    @Provides
    fun provideFactRepository(impl: FactRepositoryImpl): FactRepository = impl
}
