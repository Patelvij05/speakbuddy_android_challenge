package jp.speakbuddy.edisonandroidexercise.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.FactService
import jp.speakbuddy.edisonandroidexercise.network.datasource.FactNetworkDataSourceImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideFactNetworkDataSource(factNetworkDataSourceImpl: FactNetworkDataSourceImpl): FactNetworkDataSource =
        factNetworkDataSourceImpl

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
