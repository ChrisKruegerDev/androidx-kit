package app.moviebase.androidx.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.viewholder.BindValue
import app.moviebase.androidx.widget.recyclerview.viewholder.BindViewHolder
import app.moviebase.androidx.widget.recyclerview.viewholder.Recyclable
import app.moviebase.androidx.widget.recyclerview.viewholder.SelectionViewHolder
import kotlin.NoSuchElementException


class RecyclerAdapterHelper<T : Any>(
        private val adapter: RecyclerViewAdapterBase<T>
) {

    private val config: RecyclerAdapterConfig<T> = adapter.config
    private val dataSize: Int get() = adapter.data?.size ?: 0

    fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is Recyclable) holder.recycle()
        adapter.onClear(holder)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            ViewType.VIEW_TYPE_FOOTER -> adapter.onCreateFooter(parent, viewType)
            ViewType.VIEW_TYPE_HEADER -> config.headerViewHolderFactory?.invoke(adapter, parent)
            else                      -> adapter.onCreate(parent, viewType)
        }

        return holder ?: throw NoSuchElementException("no view holder for type '$viewType'")
    }

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>? = null) {
        if (position == 0 && holder.itemViewType == ViewType.VIEW_TYPE_HEADER)
            bindHeader(holder)
        else if (holder.itemViewType == ViewType.VIEW_TYPE_FOOTER && position == adapter.getItemCount() - 1)
            adapter.onBindFooter(holder)
        else
            onBindDefault(holder, position - if (adapter.useHeader()) 1 else 0, payloads)
    }

    fun getItemId(index: Int): Long {
        if (index == 0 && adapter.useHeader())
            return ItemId.ID_HEADER

        if (adapter.useFooter() && index >= dataSize)
            return ItemId.ID_FOOTER

        var newIndex = index
        if (adapter.useHeader())
            newIndex = index - 1

        if (newIndex in 0 until dataSize) {
            val item = adapter.getItem(newIndex) ?: return RecyclerView.NO_ID
            return config.onItemId?.getItemId(item) ?: RecyclerView.NO_ID
        } else {
            return RecyclerView.NO_ID
        }
    }

    fun getItemViewType(position: Int): Int {
        if (position == 0 && adapter.useHeader())
            return ViewType.VIEW_TYPE_HEADER

        if (adapter.useFooter() && position >= dataSize)
            return ViewType.VIEW_TYPE_FOOTER

        return onItemViewType(if (adapter.useHeader()) position - 1 else position)
    }

    fun getItemCount(): Int {
        var itemCount = if (adapter.isDataValid) dataSize else 0

        if (adapter.useHeader())
            itemCount += 1

        if (adapter.useFooter())
            itemCount += 1

        return itemCount
    }

    private fun bindHeader(holder: RecyclerView.ViewHolder) {
        if (holder is BindValue<*>) holder.bind(null)
    }

    @Suppress("UNCHECKED_CAST")
    private fun onBindDefault(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>?) {
        val value: T? = getItem(position)
        if(payloads != null) {
            (holder as? BindViewHolder<T>)?.payload(value, payloads)
            return
        }

        (holder as? BindValue<T>)?.bind(value)

        val selection = adapter.selection
        if (selection.enabled && holder is SelectionViewHolder) {
            val isSelected = selection.selectedItems.get(position, false)
            holder.containerView?.isActivated = isSelected
            holder.applyAnimation(adapter, position)
        }

        adapter.onBind(value, holder)
    }

    private fun getItem(position: Int): T? =
            if (position < 0 || position >= dataSize)
                null
            else
                adapter.getItem(position)

    private fun onItemViewType(position: Int): Int {
        val onViewType = config.onViewType ?: return ViewType.VIEW_TYPE_DEFAULT
        val item = getItem(position)
        return onViewType.getViewType(item)
    }
}
