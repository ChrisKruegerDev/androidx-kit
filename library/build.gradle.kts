plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("de.mannodermaus.android-junit5")
    id("maven-publish")
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

val version_major: String by project
val version_minor: String by project
val version_patch: String by project
val kotlin_version: String by project

group = "com.moviebase"
version = "$version_major.$version_minor.$version_patch"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("com.moviebase:android-ktx:1.2.5")

    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.browser:browser:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.paging:paging-runtime:2.1.2")
    implementation("androidx.paging:paging-runtime-ktx:2.1.2")

    api("com.github.bumptech.glide:recyclerview-integration:4.12.0")
    implementation("javax.inject:javax.inject:1")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = version_major.toInt() * 1000 + version_minor.toInt() * 100 + version_patch.toInt() * 10
        versionName = "$version_major.$version_minor.$version_patch"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}
androidExtensions {
    isExperimental = true
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

afterEvaluate {
    publishing {
        //    https://github.com/gradle/gradle/issues/11412#issuecomment-555413327
        System.setProperty("org.gradle.internal.publish.checksums.insecure", "true")

        repositories {
            maven {
                name = "bintray"
                setUrl("https://api.bintray.com/maven/moviebase/maven/android-elements/;publish=1;override=1")

                credentials {
                    username = findProperty("BINTRAY_USER") as String?
                    password = findProperty("BINTRAY_API_KEY") as String?
                }
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                groupId = "com.moviebase"
                artifactId = "android-elements"
                version = "$version_major.$version_minor.$version_patch"
                artifact(sourcesJar)
                from(components.getByName("release"))

                pom {
                    name.set("Android Elements")
                    description.set("Additional widget elements for Android.")
                    url.set("https://github.com/MoviebaseApp/${project.name}")
                    inceptionYear.set("2020")

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
                        url.set("https://github.com/MoviebaseApp/${project.name}/issues")
                    }
                    scm {
                        connection.set("scm:git:https://github.com/MoviebaseApp/${project.name}.git")
                        developerConnection.set("scm:git:git@github.com:MoviebaseApp/${project.name}.git")
                        url.set("https://github.com/MoviebaseApp/${project.name}")
                    }
                }
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}
