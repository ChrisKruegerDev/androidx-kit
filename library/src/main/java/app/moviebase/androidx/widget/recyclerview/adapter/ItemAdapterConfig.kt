package app.moviebase.androidx.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.viewholder.ItemViewHolder

interface ItemAdapterConfig<T> {
    var onClickListener: OnClickListener<T>?
    var onLongClickListener: OnLongClickListener<T>?
}

fun interface ViewHolderBuilder<T : Any> {
    fun create(adapter: ItemAdapter<T>, parent: ViewGroup): ItemViewHolder<T>
}

fun interface OnClickListener<T> {

    fun click(value: T, viewHolder: RecyclerView.ViewHolder)

    fun and(then: OnClickListener<T>): OnClickListener<T> = OnClickListener { value, viewHolder ->
        this.click(value, viewHolder)
        then.click(value, viewHolder)
    }
}

fun interface OnLongClickListener<T> {
    fun click(value: T)
}

// TODO:  add T as value type
fun interface OnViewType {
    fun getViewType(value: Any?): Int
}

class DefaultOnViewType : OnViewType {
    override fun getViewType(value: Any?): Int {
        return ViewType.VIEW_TYPE_DEFAULT
    }
}

class ClassOnViewType : OnViewType {
    override fun getViewType(value: Any?): Int {
        return value?.javaClass?.hashCode() ?: ViewType.VIEW_TYPE_DEFAULT
    }
}

fun interface OnItemId<T> {
    fun getItemId(value: T): Long
}

class OnValueHashCode<T : Any> : OnItemId<T> {
    override fun getItemId(value: T): Long = value.hashCode().toLong()
}
