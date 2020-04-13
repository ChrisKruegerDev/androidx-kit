# Placebox AndroidX
[ ![Download](https://api.bintray.com/packages/placebox/maven/placebox-androidx/images/download.svg?version=1.0.0) ](https://bintray.com/placebox/maven/placebox-androidx/1.0.0/link)

Placebox AndroidX is a additional improvement to the [AndroidX](https://developer.android.com/jetpack/androidx) library. It provides features for an easier use of user interface components.

## Downoad
Use gradle for download the latest version:

```
repositories {
  maven { url  "https://dl.bintray.com/placebox/maven" }
}

dependencies {
  implementation 'com.placebox:placebox-androidx:1.0.0'
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
Placebox AndroidX is published under the Apache 2.0 license.
