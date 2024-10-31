plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.common.data)
    implementation(libs.room.common)
    implementation(libs.kotlin.coroutines)
}