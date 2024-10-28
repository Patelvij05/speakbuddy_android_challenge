package jp.speakbuddy.edisonandroidexercise.network

import jp.speakbuddy.edisonandroidexercise.network.model.FactResponse
import retrofit2.http.GET

interface FactService {
    @GET("fact")
    suspend fun getFact(): FactResponse
}
