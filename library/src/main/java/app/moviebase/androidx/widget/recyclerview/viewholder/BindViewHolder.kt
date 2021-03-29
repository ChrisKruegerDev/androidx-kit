package app.moviebase.androidx.widget.recyclerview.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase

abstract class BindViewHolder<T : Any>(
    val adapter: RecyclerViewAdapterBase<T>,
    val parent: ViewGroup,
    @LayoutRes resource: Int
) : LayoutViewHolder(parent, resource), BindValue<T> {

    var item: T? = null

    init {
        val config = adapter.config
        val onClick = config.onClick
        val onLongClick = config.onLongClick
        val onSelection = config.onSelection

        if (onClick != null || onSelection != null)
            itemView.setOnClickListener {
                if (onClick != null)
                    item?.let { onClick(it, this) }

                if (onSelection != null)
                    item?.let {
                        var position = adapterPosition
                        if (position != RecyclerView.NO_POSITION && position < adapter.getItemCount()) {
                            if (adapter.useHeader()) position--
                            onSelection(adapter, position, it)
                        }
                    }
            }

        if (onLongClick != null)
            itemView.setOnLongClickListener {
                item?.run(onLongClick)
                item != null
            }
    }

    final override fun bind(value: T?) {
        val oldValue = item
        if (oldValue != null) unbindValue(oldValue)

        item = value
        bindValue(value)
    }

    fun payload(value: T?, payloads: List<Any>) {
        item = value
        payloadValue(payloads)
    }

    protected abstract fun bindValue(value: T?)

    protected open fun unbindValue(value: T) {
        // do something if needed
    }

    protected open fun payloadValue(payloads: List<Any>) {
        // do something if needed
    }

    fun isLastPosition(): Boolean {
        var position = adapterPosition
        if (adapter.useHeader()) position--

        val size = adapter.data?.size ?: 0
        return position == size - 1
    }

    fun isFirstPosition(): Boolean {
        var position = adapterPosition
        if (adapter.useHeader()) position--

        return position == 0
    }

}
