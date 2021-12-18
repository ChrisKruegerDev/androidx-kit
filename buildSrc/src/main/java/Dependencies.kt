object Versions {
    val versionMajor = 1
    val versionMinor = 8
    val versionPatch = 0

    val versionCode = versionMajor * 1000 + versionMinor * 100 + versionPatch * 10
    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    val minSdk = 21
    val targetSdk = 31
    val compileSdk = 31
    val buildTools = "31.0.0"

    // Plugins
    val androidGradle = "7.0.4"
    val androidJunit = "1.7.1.1"
    val dokka = "1.4.30"
    val nexus = "1.0.0"

    // Kotlin
    val kotlin = "1.6.10"
    val coroutines = "1.5.2"

    // AndroidX
    val appCompat = "1.3.1"
    val constraintLayout = "2.1.2"
    val preference = "1.1.1"
    val recyclerView = "1.2.1"
    val paging3 = "3.1.0-alpha02"

    // Google
    val material = "1.4.0"

    // Testing
    val junitJupiter = "5.8.2"
}

object Plugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    val androidJunit = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit}"
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
}

object Libs {

    object Kotlin {
        val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object AndroidX {
        val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        val browser = "androidx.browser:browser:1.3.0"
        val preferenceKtx = "androidx.preference:preference-ktx:${Versions.preference}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val coreKtx = "androidx.core:core-ktx:1.6.0-alpha02"
        val paging = "androidx.paging:paging-runtime:2.1.2"
        val pagingKtx = "androidx.paging:paging-runtime-ktx:2.1.2"
        val paging3 = "androidx.paging:paging-runtime:${Versions.paging3}"
        val pagingKtx3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
        val collection = "androidx.collection:collection:1.1.0"
        val webkit = "androidx.webkit:webkit:1.4.0"
    }

    object Google {
        val material = "com.google.android.material:material:${Versions.material}"
    }

    object Ui {
        val glideRecyclerView = "com.github.bumptech.glide:recyclerview-integration:4.12.0"
    }

    object Util {
        val inject = "javax.inject:javax.inject:1"
        val androidKtx = "app.moviebase:android-ktx:1.3.4"
    }

    object Testing {
        val truth = "com.google.truth:truth:1.1.2"
        val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
        val mockito = "org.mockito:mockito-inline:3.8.0"
        val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    }
}
