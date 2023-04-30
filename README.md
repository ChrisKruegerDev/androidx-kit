# AndroidX KIT
[![Maven Central](https://img.shields.io/maven-central/v/app.moviebase/androidx-topper?label=Maven%20Central)](https://search.maven.org/artifact/app.moviebase/androidx-topper)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-8-blue?style=flat)](https://gradle.org)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

**AndroidX KIT leverages your coding with the [AndroidX](https://developer.android.com/jetpack/androidx) libraries. This library provides features for easier use of the standard library components.**

## Setup

The library is published to Maven Central. Add the Maven Central repository if it is not already there.

```kotlin
repositories {
    mavenCentral()
}
```

To use the library in a single-platform project, add a dependency.

```kotlin
dependencies {
    implementation("app.moviebase:androidx-kit:2.0.2")
}
```

## Usage

### RecyclerView builder
Creating a RecyclerView adapter with the builder:

```kotlin
private val itemsAdapter = recyclerViewAdapter<TextItem> {
    viewHolderHeader { adapter, parent -> HeaderViewHolder(adapter, parent) }
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

### ViewModel event communication

Send your actions and messages from the ViewModel to the Activity via LiveData.

For example, start a new Activity or Fragment:

```kotlin
// in the Activity or Fragment
viewModel.action.bindTo(this)
viewModel.message.bindTo(this)

// send from the ViewModel
val action = SingleLiveEvent<Action>()

action.value = StartActivityActio(MainActivity::class)
action.value = ShowDialogFragmentAction(MyDialogFragment::clas)
```

For example, send a Snackbar message:

```kotlin
// in the Activity or Fragment
viewModel.message.bindTo(this)

// send from the ViewModel
val message = SingleLiveEvent<SnackbarMessage>()

message.value = SuccessSnackbarMessage(context, R.string.message)
```

### Open CustomTab

Open a CustomTab:

```kotlin
CustomTabHelper.openCustomTab(activity, uri, ContextCompatColors.colorSurface(activity), Logger::e)

```

### View extensions

Coming soon

### AppBar change handling

Coming soon


