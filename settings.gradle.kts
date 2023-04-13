pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


plugins {
    id("com.gradle.enterprise") version "3.13"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}

rootProject.name = "androidx-topper"

include(":androidx-topper")
include(":sample")
