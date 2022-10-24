pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "7.2.2"
        id("com.android.library") version "7.2.2"
        id("org.jetbrains.kotlin.android") version "1.7.10"
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ComposeWidget"
include(":app")
includeBuild("../PluginVersion")