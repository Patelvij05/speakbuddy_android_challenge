package jp.speakbuddy.edisonandroidexercise.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import jp.speakbuddy.edisonandroidexercise.network.ErrorInterceptor
import jp.speakbuddy.edisonandroidexercise.network.FactNetworkDataSource
import jp.speakbuddy.edisonandroidexercise.network.datasource.FactNetworkDataSourceImpl
import jp.speakbuddy.edisonandroidexercise.network.http.EdisonHttpClientBuilder
import jp.speakbuddy.edisonandroidexercise.network.http.RequestHandler
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideFactNetworkDataSource(factNetworkDataSourceImpl: FactNetworkDataSourceImpl): FactNetworkDataSource =
        factNetworkDataSourceImpl

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "catfact.ninja"

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(ErrorInterceptor())
        .build()

    @Provides
    fun provideHttpClient(
        @BaseUrl baseUrl: String,
    ): HttpClient =
        EdisonHttpClientBuilder()
            .protocol(URLProtocol.HTTPS)
            .host(baseUrl)
            .build()

    @Provides
    fun provideRequestHandler(client: HttpClient) = RequestHandler(client)
}
