pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


plugins {
    id("com.gradle.enterprise") version "3.12.5"
}

rootProject.name = "androidx-topper"

include(":androidx-topper")
include(":sample")
