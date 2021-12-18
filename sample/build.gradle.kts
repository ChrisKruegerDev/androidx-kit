plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
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

    testOptions {
        animationsDisabled = true
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    lint {
        isIgnoreTestSources = true
        isWarningsAsErrors = true
        isCheckDependencies = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
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
    implementation(Libs.Ui.glideRecyclerView)
}
