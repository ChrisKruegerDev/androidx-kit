package com.placebox.androidx.widget.recyclerview.adapter.list

import com.placebox.androidx.widget.recyclerview.adapter.AbstractListRecyclerAdapter
import com.placebox.androidx.widget.recyclerview.adapter.RecyclerAdapterConfig

fun <T : Any> lazyRecyclerViewAdapter(block: DefaultRecyclerAdapterConfig<T>.() -> Unit) = lazy {
    recyclerViewAdapter<T>(block)
}

fun <T : Any> recyclerViewAdapter(block: DefaultRecyclerAdapterConfig<T>.() -> Unit): ListRecyclerViewAdapter<T> {
    val config = DefaultRecyclerAdapterConfig<T>().apply(block)

    if (config.viewHolderFactories.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListRecyclerViewAdapter(config, config.glideConfig)
}

class ListRecyclerViewAdapter<T : Any>(
    config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>
) : AbstractListRecyclerAdapter<T>(config), GlideRecyclerAdapter<T>
