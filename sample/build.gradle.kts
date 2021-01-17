plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

val version_major: String by project
val version_minor: String by project
val version_patch: String by project
val kotlin_version: String by project

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = version_major.toInt() * 1000 + version_minor.toInt() * 100 + version_patch.toInt() * 10
        versionName = "$version_major.$version_minor.$version_patch"
        applicationId = "com.moviebase.androidx.sample"
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
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("com.google.android.material:material:1.3.0-rc01")
}
