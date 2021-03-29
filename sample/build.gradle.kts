plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        applicationId = "app.moviebase.androidx.sample"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dexOptions {
        preDexLibraries = true
        javaMaxHeapSize = "12g"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
    lintOptions {
        isIgnoreTestSources = true
        isWarningsAsErrors = true
        isCheckDependencies = true
        isAbortOnError = false
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation (project(":library"))

    implementation(Libs.Kotlin.kotlin)
    implementation(Libs.Kotlin.kotlinReflect)

    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.recyclerView)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.Google.material)
}
