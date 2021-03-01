package com.moviebase.androidx.widget.recyclerview.adapter.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviebase.androidx.widget.recyclerview.adapter.*

fun <T : Any> lazyListAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit) = lazy {
    listAdapter<T>(block)
}

fun <T : Any> listAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit): ListAdapter<T> {
    val config = GlideRecyclerAdapterConfig<T>().apply(block)

    if (config.viewHolderFactories.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListAdapter(config, config.glideConfig)
}

class ListAdapter<T : Any>(
    override val config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>
) : androidx.recyclerview.widget.ListAdapter<T, RecyclerView.ViewHolder>(config.itemDiffCallback),
    RecyclerViewAdapterBase<T>,
    GlideRecyclerAdapter<T> {

    override val isDataValid: Boolean get() = true
    override val data: List<T> get() = currentList
    override val selection = Selection(this)

    private val helper by lazy { RecyclerAdapterHelper<T>(this) }

    init {
        setHasStableIds(config.onItemId != null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        helper.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        helper.onBindViewHolder(holder, position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        helper.onBindViewHolder(holder, position, if(payloads.isEmpty()) null else payloads)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        helper.onViewRecycled(holder)
    }

    override fun getItem(position: Int): T? = super.getItem(position)

    override fun getItemCount(): Int = helper.getItemCount()

    override fun getItemViewType(position: Int): Int = helper.getItemViewType(position)

    override fun getItemId(index: Int): Long = helper.getItemId(index)

}
