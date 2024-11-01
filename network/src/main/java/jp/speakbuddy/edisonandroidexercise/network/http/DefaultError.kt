package jp.speakbuddy.edisonandroidexercise.network.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultError(
    @SerialName("message")
    val message: String,
)