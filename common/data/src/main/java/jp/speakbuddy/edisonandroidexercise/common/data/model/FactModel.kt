package jp.speakbuddy.edisonandroidexercise.common.data.model

import kotlinx.serialization.Serializable

interface Fact {
    val fact: String
}

@Serializable
data class FactModel(override val fact: String, val length: Int) : Fact

val Fact.length
    get() = fact.length

val Fact.isShowLength
    get() = length > 100

val Fact.containCats
    get() = fact.contains("cats")
