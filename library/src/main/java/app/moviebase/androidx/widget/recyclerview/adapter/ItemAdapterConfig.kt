package app.moviebase.androidx.widget.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView

interface ItemAdapterConfig<T> {
    var onClickListener: OnClickListener<T>?
    var onLongClickListener: OnLongClickListener<T>?
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

fun interface OnViewType {
    fun getViewType(value: Any?): Int
}

class ClassViewType: OnViewType {
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
