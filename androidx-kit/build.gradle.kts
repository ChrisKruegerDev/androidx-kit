import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ben.manes.versions)
    alias(libs.plugins.maven.publish)
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.kotlin.reflect)

    api(libs.androidx.recyclerview)
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.preference.ktx)
    api(libs.androidx.browser)
    api(libs.androidx.paging)
    api(libs.androidx.paging.ktx)
    api(libs.androidx.work.ktx)
    api(libs.androidx.navigation.ui)
    api(libs.androidx.navigation.fragment.ktx)

    api(libs.google.material)

    api(libs.inject)
    api(libs.glide.recyclerview)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.kotlin.junit5)
    implementation(libs.junit)
    implementation(libs.junit.jupiter.api)
    runtimeOnly(libs.junit.jupiter.engine)
    implementation(libs.truth)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "app.moviebase.androidx"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        animationsDisabled = true
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
