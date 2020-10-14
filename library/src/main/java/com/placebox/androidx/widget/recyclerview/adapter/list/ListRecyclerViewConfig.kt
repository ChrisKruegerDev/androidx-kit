package com.placebox.androidx.widget.recyclerview.adapter.list

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.placebox.androidx.widget.recyclerview.adapter.RecyclerAdapterConfig
import com.placebox.androidx.widget.recyclerview.diff.ItemDiffCallback

open class DefaultRecyclerAdapterConfig<T : Any> : RecyclerAdapterConfig<T>() {

    var diffCallback: DiffUtil.ItemCallback<T> = ItemDiffCallback()
    var glideConfig: GlideConfig<T> = GlideConfig()

    var glideLoader: GlideViewLoader<in T>?
        get() = glideConfig.loader
        set(value) {
            glideConfig.loader = value
        }

    fun glide(block: GlideConfig<T>.() -> Unit) = glideConfig.apply(block)

}

class GlideConfig<T> {
    val preloadProvider: ViewPreloadSizeProvider<T> = ViewPreloadSizeProvider()
    var loader: GlideViewLoader<in T>? = null
}



