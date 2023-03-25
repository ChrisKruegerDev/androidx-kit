package app.moviebase.androidx.widget.recyclerview.adapter


import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import app.moviebase.androidx.widget.recyclerview.adapter.ViewType.VIEW_TYPE_DEFAULT
import app.moviebase.androidx.widget.recyclerview.diff.ItemDiffCallback
import app.moviebase.androidx.widget.recyclerview.viewholder.BindViewHolder
import kotlin.reflect.KClass

typealias OnSelection<T> = (adapter: RecyclerViewAdapterBase<T>, position: Int, value: T) -> Unit
typealias ViewHolderFactory<T> = (adapter: RecyclerViewAdapterBase<T>, parent: ViewGroup) -> BindViewHolder<T>?

open class RecyclerAdapterConfig<T: Any> : ItemAdapterConfig<T> {

    var orientation = LinearLayout.VERTICAL
    override var onClickListener: OnClickListener<T>? = null
    override var onLongClickListener: OnLongClickListener<T>? = null
    var onSelection: OnSelection<T>? = null
    val viewHolderFactories = mutableMapOf<Int, ViewHolderFactory<T>>()
    var headerViewHolderFactory: ViewHolderFactory<T>? = null
    var footerViewHolderFactory: ViewHolderFactory<T>? = null
    var onViewType: OnViewType? = null
    var onItemId: OnItemId<T>? = null
    var itemDiffCallback: DiffUtil.ItemCallback<T> = ItemDiffCallback()

    fun onViewType(onViewType: OnViewType) {
        this.onViewType = onViewType
    }

    fun onSelection(onSelection: OnSelection<T>) {
        this.onSelection = onSelection
    }

    fun onClick(onClick: (T) -> Unit) {
        this.onClickListener = OnClickListener { value, _ -> onClick(value) }
    }

    fun onItemId(onItemId: OnItemId<T>) {
        this.onItemId = onItemId
    }

    fun onLongClick(onLongClickListener: OnLongClickListener<T>) {
        this.onLongClickListener = onLongClickListener
    }

    fun viewHolder(factory: ViewHolderFactory<T>) {
        viewHolderFactories[VIEW_TYPE_DEFAULT] = factory
    }

    fun viewHolder(viewType: Int, factory: ViewHolderFactory<T>) {
        viewHolderFactories[viewType] = factory
    }

    fun viewHolder(viewType: KClass<out T>, factory: ViewHolderFactory<T>) {
        onViewType = ClassOnViewType()
        viewHolderFactories[viewType.java.hashCode()] = factory
    }

    fun headerViewHolder(factory: ViewHolderFactory<T>) {
        headerViewHolderFactory = factory
    }

    fun footerViewHolder(factory: ViewHolderFactory<T>) {
        footerViewHolderFactory = factory
    }
}

