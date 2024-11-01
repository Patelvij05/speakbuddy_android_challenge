plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.common)
    implementation(projects.network)
    implementation(projects.database)
    implementation(projects.common.data)
    implementation(projects.common.utils)
    implementation(projects.common.domain)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines)
}