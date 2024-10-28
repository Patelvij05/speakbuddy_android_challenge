package jp.speakbuddy.edisonandroidexercise.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.FactHandlerImpl
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.FactService
import jp.speakbuddy.edisonandroidexercise.network.datasource.FactNetworkDataSourceImpl
import jp.speakbuddy.edisonandroidexercise.storage.FactHandler
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideFactHandler(factHandler: FactHandlerImpl): FactHandler = factHandler

    @Provides
    fun provideFactNetworkDataSource(factHandler: FactNetworkDataSourceImpl): FactNetworkDataSource = factHandler

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://catfact.ninja/"

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideFactService(retrofit: Retrofit): FactService = retrofit.create(FactService::class.java)
}
