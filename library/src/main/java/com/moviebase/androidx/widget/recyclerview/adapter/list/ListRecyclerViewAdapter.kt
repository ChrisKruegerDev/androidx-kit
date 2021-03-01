package com.moviebase.androidx.widget.recyclerview.adapter.list

import com.moviebase.androidx.widget.recyclerview.adapter.AbstractListRecyclerAdapter
import com.moviebase.androidx.widget.recyclerview.adapter.RecyclerAdapterConfig

fun <T : Any> lazyRecyclerViewAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit) = lazy {
    recyclerViewAdapter(block)
}

fun <T : Any> recyclerViewAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit): ListRecyclerViewAdapter<T> {
    val config = GlideRecyclerAdapterConfig<T>().apply(block)

    if (config.viewHolderFactories.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListRecyclerViewAdapter(config, config.glideConfig)
}

/**
 * Use ListAdapter if only a list without additional features is necessary.
 */
class ListRecyclerViewAdapter<T : Any>(
    config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>
) : AbstractListRecyclerAdapter<T>(config), GlideRecyclerAdapter<T>
