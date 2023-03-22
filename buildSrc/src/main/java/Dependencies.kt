object Versions {
    val versionMajor = 1
    val versionMinor = 9
    val versionPatch = 0

    val versionCode = versionMajor * 1000 + versionMinor * 100 + versionPatch * 10
    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    val minSdk = 21
    val targetSdk = 33
    val compileSdk = 33
    val buildTools = "33.0.0"

    // Plugins
    val androidGradle = "7.4.0"
    val androidJunit = "1.7.1.1"
    val dokka = "1.4.30"
    val nexus = "1.0.0"
    val benManesVersions = "0.44.0"

    // Kotlin
    val kotlin = "1.8.0"
    val coroutines = "1.6.4"

    // AndroidX
    val appCompat = "1.6.0"
    val core = "1.9.0"
    val constraintLayout = "2.1.3"
    val preference = "1.2.0"
    val recyclerView = "1.3.0-rc01"
    val paging3 = "3.2.0-alpha01"
    val browser = "1.4.0"
    val collection = "1.2.0"
    val webkit = "1.4.0"

    // Google
    val material = "1.7.0"

    // UI
    val glide = "4.14.2"

    // Util
    val androidKtx = "1.4.2"
    val javaInject = "1"

    // Testing
    val junitJupiter = "5.9.2"
    val truth = "1.1.3"
    val mockitoInline = "5.0.0"
    val mockitoKotlin = "4.1.0"
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
        val browser = "androidx.browser:browser:${Versions.browser}"
        val preferenceKtx = "androidx.preference:preference-ktx:${Versions.preference}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val coreKtx = "androidx.core:core-ktx:${Versions.core}"
        val paging = "androidx.paging:paging-runtime:2.1.2"
        val pagingKtx = "androidx.paging:paging-runtime-ktx:2.1.2"
        val paging3 = "androidx.paging:paging-runtime:${Versions.paging3}"
        val pagingKtx3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
        val collection = "androidx.collection:collection:${Versions.collection}"
        val webkit = "androidx.webkit:webkit:${Versions.webkit}"
    }

    object Google {
        val material = "com.google.android.material:material:${Versions.material}"
    }

    object Ui {
        val glideRecyclerView = "com.github.bumptech.glide:recyclerview-integration:${Versions.glide}"
    }

    object Util {
        val inject = "javax.inject:javax.inject:${Versions.javaInject}"
        val androidKtx = "app.moviebase:android-ktx:${Versions.androidKtx}"
    }

    object Testing {
        val truth = "com.google.truth:truth:${Versions.truth}"
        val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
        val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
        val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    }
}
