package jp.speakbuddy.edisonandroidexercise.common.utils

interface Mapper<F, T> {
    fun map(from: F): T
}
