package app.moviebase.androidx.widget.recyclerview.adapter.list

import com.bumptech.glide.util.ViewPreloadSizeProvider
import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerAdapterConfig

open class GlideRecyclerAdapterConfig<T : Any> : RecyclerAdapterConfig<T>() {

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



