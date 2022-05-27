package app.moviebase.androidx.widget.recyclerview.list

import androidx.recyclerview.widget.DiffUtil
import app.moviebase.androidx.widget.recyclerview.adapter.*
import app.moviebase.androidx.widget.recyclerview.diff.ItemDiffCallback
import app.moviebase.androidx.widget.recyclerview.glide.GlideConfig
import app.moviebase.androidx.widget.recyclerview.glide.GlideViewLoader
import kotlin.reflect.KClass

@Suppress("unused")
open class ListItemAdapterConfig<T : Any> : ItemAdapterConfig<T> {

    override var onClickListener: OnClickListener<T>? = null
    override var onLongClickListener: OnLongClickListener<T>? = null

    val viewHolders = mutableMapOf<Int, ViewHolderBuilder<T>>()

    var onViewType: OnViewType = DefaultOnViewType()
    var onItemId: OnItemId<T>? = OnValueHashCode()
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

    fun onItemId(onItemId: OnItemId<T>) {
        this.onItemId = onItemId
    }

    fun onClick(onClick: (T) -> Unit) {
        onClickListener = OnClickListener { value, _ -> onClick(value) }
    }

    fun onLongClick(onClick: (T) -> Unit) {
        onLongClickListener = OnLongClickListener { value -> onClick(value) }
    }

    fun viewHolder(builder: ViewHolderBuilder<T>) {
        viewHolders[ViewType.VIEW_TYPE_DEFAULT] = builder
    }

    fun viewHolderHeader(builder: ViewHolderBuilder<T>) {
        viewHolders[ViewType.VIEW_TYPE_HEADER] = builder
    }

    fun viewHolderAd(builder: ViewHolderBuilder<T>) {
        viewHolders[ViewType.VIEW_TYPE_AD] = builder
    }

    fun viewHolder(viewType: Int, builder: ViewHolderBuilder<T>) {
        viewHolders[viewType] = builder
    }

    fun viewHolder(viewType: KClass<out T>, builder: ViewHolderBuilder<T>) {
        onViewType = ClassOnViewType()
        viewHolders[viewType.java.hashCode()] = builder
    }
}
