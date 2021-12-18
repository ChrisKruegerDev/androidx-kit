plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("maven-publish")
    signing
    id("org.jetbrains.dokka")
    id("com.github.ben-manes.versions") version "0.39.0"
}

group = "app.moviebase"
version = Versions.versionName

dependencies {
    implementation(Libs.Kotlin.kotlin)
    implementation(Libs.Kotlin.kotlinReflect)

    implementation(Libs.AndroidX.recyclerView)
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.preferenceKtx)
    implementation(Libs.AndroidX.browser)
    implementation(Libs.AndroidX.paging)
    implementation(Libs.AndroidX.pagingKtx)
    implementation(Libs.AndroidX.paging3)
    implementation(Libs.AndroidX.pagingKtx3)

    implementation(Libs.Google.material)

    implementation(Libs.Util.androidKtx)
    implementation(Libs.Util.inject)
    implementation(Libs.Ui.glideRecyclerView)

    testImplementation(Libs.Testing.truth)
    testImplementation(Libs.Testing.jupiter)
    testRuntimeOnly(Libs.Testing.jupiterEngine)
    testImplementation(Libs.Testing.mockito)
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

androidExtensions {
    isExperimental = true
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

artifacts {
    add("archives", sourcesJar)
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "sonatype"
                if(Versions.versionName.endsWith("-SNAPSHOT"))
                    setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                else
                    setUrl("https://s01.oss.sonatype.org/service/local/")

                credentials {
                    username = findProperty("SONATYPE_USER") as String?
                    password = findProperty("SONATYPE_PASSWORD") as String?
                }
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                groupId = "app.moviebase"
                artifactId = "android-elements"
                version = Versions.versionName

                artifact(sourcesJar)
                from(components.getByName("release"))

                pom {
                    name.set("Android Elements")
                    description.set("Additional widget elements for Android.")
                    url.set("https://github.com/MoviebaseApp/android-elements")
                    inceptionYear.set("2021")

                    developers {
                        developer {
                            id.set("chrisnkrueger")
                            name.set("Christian Krüger")
                            email.set("christian.krueger@moviebase.app")
                        }
                    }
                    licenses {
                        license {
                            name.set("The Apache Software License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    issueManagement {
                        system.set("GitHub Issues")
                        url.set("https://github.com/MoviebaseApp/android-elements/issues")
                    }
                    scm {
                        connection.set("scm:git:https://github.com/MoviebaseApp/android-elements.git")
                        developerConnection.set("scm:git:git@github.com:MoviebaseApp/android-elements.git")
                        url.set("https://github.com/MoviebaseApp/android-elements")
                    }
                }
            }
        }
    }
    signing {
        sign(publishing.publications)
    }
}

