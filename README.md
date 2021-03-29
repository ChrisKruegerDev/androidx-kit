# Android Elements
[ ![Download](https://api.bintray.com/packages/moviebase/maven/android-elements/images/download.svg?version=1.3.0) ](https://bintray.app.moviebase.maven/android-elements/1.3.0/link)

Android Elements is an additional improvement to the [AndroidX](https://developer.android.com/jetpack/androidx) library. It provides features for an easier use of user interface components.

## Downoad
Use gradle for download the latest version:

```
dependencies {
  implementation 'app.moviebase.android-elements:1.3.0'
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

## License
Android Elements is published under the Apache 2.0 license.
