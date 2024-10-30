pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "edison_android_exercise"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":theme")
include(":network")
include(":common:data")
include(":feature:fact")
include(":feature:fact:data")
include(":feature:fact:domain")
include(":common:utils")
include(":common:domain")
include(":database")
include(":feature:facthistory")
include(":feature:facthistory:data")
include(":feature:facthistory:domain")
