package app.moviebase.androidx.widget.recyclerview.glide

import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerAdapterConfig

class GlideRecyclerAdapterConfig<T : Any> : RecyclerAdapterConfig<T>() {

    var glideConfig: GlideConfig<T> = GlideConfig()
    var glideLoader: GlideViewLoader<in T>?
        get() = glideConfig.loader
        set(value) {
            glideConfig.loader = value
        }

    fun glide(block: GlideConfig<T>.() -> Unit) = glideConfig.apply(block)

}



