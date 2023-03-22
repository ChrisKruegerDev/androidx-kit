//import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.ben.manes.versions) apply false
//    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.maven.publish) apply false
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }

//    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
//    configure<SpotlessExtension> {
//        kotlin {
//            target("**/*.kt")
//            targetExclude("$buildDir/**/*.kt")
//            targetExclude("bin/**/*.kt")
//            ktlint(libs.versions.ktlint.get())
//        }
//        kotlinGradle {
//            target("**/*.kts")
//            targetExclude("$buildDir/**/*.kts")
//            ktlint(libs.versions.ktlint.get())
//        }
//    }

//    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
//        compilerOptions {
//            // Treat all Kotlin warnings as errors
//            allWarningsAsErrors.set(true)
//
//            // Enable experimental coroutines APIs, including Flow
//            freeCompilerArgs.addAll(
//                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
//                "-opt-in=kotlinx.coroutines.FlowPreview"
//            )
//        }
//    }
}
