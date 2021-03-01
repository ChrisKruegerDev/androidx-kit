package com.moviebase.androidx.widget.recyclerview.paging

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.ListPreloader
import com.moviebase.androidx.widget.recyclerview.adapter.*
import com.moviebase.androidx.widget.recyclerview.adapter.list.GlideConfig
import com.moviebase.androidx.widget.recyclerview.adapter.list.GlideRecyclerAdapter
import com.moviebase.androidx.widget.recyclerview.adapter.list.GlideRecyclerAdapterConfig

fun <T : Any> lazyPagedAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit) = lazy { pagedAdapter(block) }

fun <T : Any> pagedAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit): PagedAdapter<T> {
    val config = GlideRecyclerAdapterConfig<T>()
    config.onItemId = OnValueHashCode()
    config.apply(block)

    return PagedAdapter(config, config.glideConfig, config.itemDiffCallback)
}

class PagedAdapter<T : Any>(
    override val config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>,
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(diffCallback), ListPreloader.PreloadModelProvider<T>, RecyclerViewAdapterBase<T>, GlideRecyclerAdapter<T> {

    override val selection: Selection = Selection(this)
    override val isDataValid: Boolean get() = currentList != null
    override val data: List<T> get() = currentList ?: emptyList()

    private val helper = RecyclerAdapterHelper(this)
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            helper.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (holder) {
        is HorizontalLoadingViewHolder -> holder.bind(networkState)
        is VerticalLoadingViewHolder   -> holder.bind(networkState)
        else                           -> helper.onBindViewHolder(holder, position)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        helper.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        // use paged item count
        var itemCount = super.getItemCount()

        if (useHeader())
            itemCount++

        if (useFooter())
            itemCount++

        return itemCount
    }

    override fun getItemViewType(position: Int): Int = helper.getItemViewType(position)

    override fun getItemId(index: Int): Long = helper.getItemId(index)

    override fun getItem(position: Int): T? = super.getItem(position)

    override fun getPreloadItems(position: Int): MutableList<T> {
        val list = currentList

        val listPosition = if (isLoading()) position - 1 else position

        return if (listPosition < 0 || list == null || listPosition >= list.size)
            mutableListOf()
        else
            list.subList(listPosition, listPosition + 1)
    }

    override fun onCreateFooter(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? =
         if (config.orientation == LinearLayout.VERTICAL)
             HorizontalLoadingViewHolder(parent)
         else
             VerticalLoadingViewHolder(parent)

    override fun useFooter(): Boolean = isLoading()

    fun setNetworkState(newNetworkState: NetworkState?) {
        val prevNetworkState = networkState
        val isLoading = isLoading()
        networkState = newNetworkState

        if (isLoading != isLoading()) {
            val itemCount = super.getItemCount() + if (useHeader()) 1 else 0

            if (isLoading)
                notifyItemRemoved(itemCount)
            else
                notifyItemInserted(itemCount)
        } else if (isLoading && prevNetworkState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun isLoading() = networkState == NetworkState.LOADING

}
