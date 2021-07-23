package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.*
import app.moviebase.androidx.widget.recyclerview.adapter.list.GlideConfig
import app.moviebase.androidx.widget.recyclerview.adapter.list.GlideViewLoader
import app.moviebase.androidx.widget.recyclerview.viewholder.ItemViewHolder

fun interface ViewHolderBuilder<T: Any> {
    fun create(adapter: RecyclerView.Adapter<*>, parent: ViewGroup): ItemViewHolder<T>
}

fun interface LoadStateViewHolderBuilder {
    fun create(adapter: PagingAdapter<*>, parent: ViewGroup): LoadStateViewHolder
}

class PagingAdapterConfig<T : Any>: ItemAdapterConfig<T> {

    var glideConfig: GlideConfig<T> = GlideConfig()
    var glideLoader: GlideViewLoader<in T>?
        get() = glideConfig.loader
        set(value) {
            glideConfig.loader = value
        }

    var onViewType: OnViewType? = null
    override var onClick: OnClick<T>? = null
    override var onLongClick: OnLongClick<T>? = null
    val viewHolders = mutableMapOf<Int, ViewHolderBuilder<T>>()
    var loadStateViewHolder: LoadStateViewHolderBuilder? = null

    var onItemId: OnItemId<T>? = null
    lateinit var diffCallback: DiffUtil.ItemCallback<T>

    fun glide(block: GlideConfig<T>.() -> Unit) = glideConfig.apply(block)

}
