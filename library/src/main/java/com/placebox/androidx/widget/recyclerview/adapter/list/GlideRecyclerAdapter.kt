package com.placebox.androidx.widget.recyclerview.adapter.list

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.placebox.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import com.placebox.androidx.widget.recyclerview.viewholder.ImageViewHolder

interface GlideRecyclerAdapter<T : Any> : RecyclerViewAdapterBase<T>, ListPreloader.PreloadModelProvider<T> {

    val glideConfig: GlideConfig<T>

    val preloadProvider: ViewPreloadSizeProvider<T>
        get() = glideConfig.preloadProvider

    val requests: RequestManager
        get() = glideConfig.loader?.requests ?: throw IllegalStateException("no glide loader available")

    override fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        val holder = super.onCreate(parent, viewType)
        if (holder is ImageViewHolder) preloadProvider.setView(holder.imageView)
        return holder
    }

    override fun onBind(value: T?, holder: RecyclerView.ViewHolder) {
        super.onBind(value, holder)

        if (holder is ImageViewHolder) {
            val requestBuilder = glideConfig.loader?.load(value, holder)
            requestBuilder?.into(holder.imageView)?.waitForLayout()
            glideConfig.transitionNameProvider?.let { provider -> holder.imageView.transitionName = value?.let { provider(it) } }
        }
    }

    override fun onClear(holder: RecyclerView.ViewHolder) {
        super.onClear(holder)
        if (holder is ImageViewHolder) glideConfig.loader?.clearGlide(holder.imageView)
    }

    override fun getPreloadRequestBuilder(item: T): RequestBuilder<Drawable>? {
        return glideConfig.loader?.preload(item, null)
    }

    override fun getPreloadItems(position: Int): List<T> {
        val currentData = data
        return if (position < 0 || currentData == null || position >= currentData.size)
            emptyList()
        else
            currentData.subList(position, position + 1)
    }
}
