package jp.speakbuddy.edisonandroidexercise.common.domain.model

sealed class Resource<out T> {
    data class Error(val e: ResourceError) : Resource<Nothing>()
    data class Success<R>(val result: R) : Resource<R>()
}