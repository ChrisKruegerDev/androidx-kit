package app.moviebase.androidx.widget.recyclerview.glide

import com.bumptech.glide.util.ViewPreloadSizeProvider

class GlideConfig<T> {
    val preloadProvider: ViewPreloadSizeProvider<T> = ViewPreloadSizeProvider()
    var loader: GlideViewLoader<in T>? = null
}
