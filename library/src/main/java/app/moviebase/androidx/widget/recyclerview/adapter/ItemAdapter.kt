package app.moviebase.androidx.widget.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.list.GlideConfig
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestManager
import com.bumptech.glide.util.ViewPreloadSizeProvider

interface ItemAdapter<T> {
    val config: ItemAdapterConfig<T>
    fun getItemCount(): Int
}

interface ItemAdapterConfig<T> {
    var onClick: OnClick<T>?
    var onLongClick: OnLongClick<T>?
}

interface GlideItemAdapter<T : Any> : ListPreloader.PreloadModelProvider<T> {

    val glideConfig: GlideConfig<T>

    val preloadProvider: ViewPreloadSizeProvider<T>
        get() = glideConfig.preloadProvider

    val requests: RequestManager
        get() = glideConfig.loader?.requests ?: throw IllegalStateException("no glide loader available")
}

fun interface OnClick<T> {

    fun click(value: T, viewHolder: RecyclerView.ViewHolder)

    fun and(then: OnClick<T>): OnClick<T> = OnClick { value, viewHolder ->
        this.click(value, viewHolder)
        then.click(value, viewHolder)
    }
}

fun interface OnLongClick<T> {
    fun click(value: T)
}

typealias OnViewType = (Any?) -> Int
typealias OnItemId<T> = (T) -> Long

