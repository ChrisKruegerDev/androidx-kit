package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import app.moviebase.androidx.widget.recyclerview.adapter.*
import app.moviebase.androidx.widget.recyclerview.glide.GlideConfig
import app.moviebase.androidx.widget.recyclerview.glide.GlideViewLoader
import app.moviebase.androidx.widget.recyclerview.diff.ItemDiffCallback
import app.moviebase.androidx.widget.recyclerview.viewholder.ItemViewHolder
import kotlin.reflect.KClass

fun interface ViewHolderBuilder<T : Any> {
    fun create(adapter: ItemAdapter<T>, parent: ViewGroup): ItemViewHolder<T>
}

fun interface LoadStateViewHolderBuilder {
    fun create(adapter: PagingAdapter<*>, parent: ViewGroup): LoadStateViewHolder
}

class PagingAdapterConfig<T : Any> : ItemAdapterConfig<T> {

    var orientation = LinearLayout.VERTICAL

    var onViewType: OnViewType? = null
    var onItemId: OnItemId<T>? = null

    override var onClickListener: OnClickListener<T>? = null
    override var onLongClickListener: OnLongClickListener<T>? = null

    val viewHolders = mutableMapOf<Int, ViewHolderBuilder<T>>()
    var loadStateViewHolder: LoadStateViewHolderBuilder? = null

    var diffCallback: DiffUtil.ItemCallback<T> = ItemDiffCallback()

    var glideConfig: GlideConfig<T> = GlideConfig()
    var glideLoader: GlideViewLoader<in T>?
        get() = glideConfig.loader
        set(value) {
            glideConfig.loader = value
        }

    fun onViewType(onViewType: OnViewType) {
        this.onViewType = onViewType
    }

    fun onClick(onClick: (T) -> Unit) {
        this.onClickListener = OnClickListener { value, _ -> onClick(value) }
    }

    fun onItemId(onItemId: OnItemId<T>) {
        this.onItemId = onItemId
    }

    fun viewHolder(builder: ViewHolderBuilder<T>) {
        viewHolders[ViewType.VIEW_TYPE_DEFAULT] = builder
    }

    fun viewHolder(viewType: Int, builder: ViewHolderBuilder<T>) {
        viewHolders[viewType] = builder
    }

    fun viewHolder(viewType: KClass<out T>, builder: ViewHolderBuilder<T>) {
        onViewType = ClassViewType()
        viewHolders[viewType.java.hashCode()] = builder
    }

    fun loadStateViewHolder(builder: LoadStateViewHolderBuilder) {
        loadStateViewHolder = builder
    }

    fun glide(block: GlideConfig<T>.() -> Unit) = glideConfig.apply(block)
}
