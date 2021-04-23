plugins {
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", Versions.kotlin))
        classpath(Plugins.androidGradle)
        classpath(Plugins.androidJunit)
        classpath(Plugins.dokka)
    }
}


group = "app.moviebase"
version = Versions.versionName

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(findProperty("SONATYPE_USER") as String?)
            password.set(findProperty("SONATYPE_PASSWORD") as String?)
            stagingProfileId.set(findProperty("SONATYPE_STAGING_PROFILE_ID") as String?)
        }
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
    }
}

