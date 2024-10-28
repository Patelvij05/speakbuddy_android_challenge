plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.network)
    implementation(projects.storage)
    implementation(projects.common.data)
    implementation(projects.common.utils)
    implementation(projects.database)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.javax.inject)
}