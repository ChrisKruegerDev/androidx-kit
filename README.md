[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/android-elements?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/android-elements)
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-7-blue?style=flat)](https://gradle.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Android Elements
Android Elements is an additional improvement to the [AndroidX](https://developer.android.com/jetpack/androidx) library. It provides features for an easier use of user interface components.

## Adding to your project

The library is published to Maven Central. Add the Maven Central repository if it is not already there.

```kotlin
repositories {
    mavenCentral()
}
```

To use the library in a single-platform project, add a dependency.

```kotlin
dependencies {
    implementation("app.moviebase:android-elements:1.6.1")
}
```

## Usage
Creating a RecyclerView adapter with the builder:

```
private val itemsAdapter = recyclerViewAdapter<TextItem> {
    headerViewHolder { adapter, parent -> HeaderViewHolder(adapter, parent) }
    viewHolder { adapter, parent -> TextViewHolder(adapter, parent) }

    onItemId { it.id }

    onClick {
        Toast.makeText(this@MainActivity, "Click on ${it.text}", Toast.LENGTH_SHORT).show()
    }

    onLongClick {
        Toast.makeText(this@MainActivity, "Long click on ${it.text}", Toast.LENGTH_SHORT).show()
    }
}
```
