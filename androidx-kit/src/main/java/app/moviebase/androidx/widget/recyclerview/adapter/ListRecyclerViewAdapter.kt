package app.moviebase.androidx.widget.recyclerview.adapter

import app.moviebase.androidx.widget.recyclerview.glide.GlideConfig
import app.moviebase.androidx.widget.recyclerview.glide.GlideRecyclerAdapter
import app.moviebase.androidx.widget.recyclerview.glide.GlideRecyclerAdapterConfig

@Deprecated(
    "use the native solution with ListAdapter",
    ReplaceWith("lazyListAdapter", "app.moviebase.androidx.widget.recyclerview.adapter.lazyListAdapter")
)
fun <T : Any> lazyRecyclerViewAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit) = lazy {
    recyclerViewAdapter(block)
}

@Deprecated(
    "use the native solution with ListAdapter",
    ReplaceWith("listAdapter", "app.moviebase.androidx.widget.recyclerview.adapter.listAdapter")
)
fun <T : Any> recyclerViewAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit): ListRecyclerViewAdapter<T> {
    val config = GlideRecyclerAdapterConfig<T>().apply(block)

    if (config.viewHolderFactories.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListRecyclerViewAdapter(config, config.glideConfig)
}

/**
 * Use ListAdapter if only a list without additional features is necessary.
 */
@Deprecated(
    "use the native solution with ListAdapter",
    ReplaceWith("ListAdapter", "app.moviebase.androidx.widget.recyclerview.adapter.ListAdapter")
)
class ListRecyclerViewAdapter<T : Any>(
    config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>
) : AbstractListRecyclerAdapter<T>(config), GlideRecyclerAdapter<T>
