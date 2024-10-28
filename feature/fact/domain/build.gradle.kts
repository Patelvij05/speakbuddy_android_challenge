plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.network)
    implementation(projects.storage)
    implementation(projects.common.data)
    implementation(projects.common.utils)
    implementation(projects.common.domain)
    implementation(projects.feature.fact.data)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines)
}