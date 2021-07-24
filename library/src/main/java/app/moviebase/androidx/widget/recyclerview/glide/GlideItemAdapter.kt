package app.moviebase.androidx.widget.recyclerview.glide

import app.moviebase.androidx.widget.recyclerview.adapter.ItemAdapter
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.util.ViewPreloadSizeProvider

interface GlideItemAdapter<T : Any> : ItemAdapter<T>, ListPreloader.PreloadModelProvider<T> {

    val glideConfig: GlideConfig<T>

    val preloadProvider: ViewPreloadSizeProvider<T>
        get() = glideConfig.preloadProvider

    val requests: RequestManager
        get() = glideConfig.loader?.requests ?: throw IllegalStateException("no glide loader available")

    override fun getPreloadItems(position: Int): List<T> {
        val itemCount = getItemCount()
        return if (position < 0 || itemCount == 0 || position >= itemCount)
            emptyList()
        else {
            val elements = getItemBy(position + 1) ?: return emptyList()
            mutableListOf(elements)
        }
    }

    override fun getPreloadRequestBuilder(item: T): RequestBuilder<*>? {
        return glideConfig.loader?.preload(item, null)
    }
}
