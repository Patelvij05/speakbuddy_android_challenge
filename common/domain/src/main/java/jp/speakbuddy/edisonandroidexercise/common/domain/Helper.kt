package jp.speakbuddy.edisonandroidexercise.common.domain

import jp.speakbuddy.edisonandroidexercise.common.domain.model.Resource
import jp.speakbuddy.edisonandroidexercise.common.domain.model.ResourceError
import jp.speakbuddy.edisonandroidexercise.network.NetworkException
import jp.speakbuddy.edisonandroidexercise.network.NetworkResult

fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when (exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        is NetworkException.UnknownException -> Resource.Error(ResourceError.UNKNOWN)
    }
}