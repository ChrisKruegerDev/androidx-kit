package app.moviebase.androidx.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.glide.GlideConfig
import app.moviebase.androidx.widget.recyclerview.glide.GlideRecyclerAdapter
import app.moviebase.androidx.widget.recyclerview.glide.GlideRecyclerAdapterConfig

fun <T : Any> lazyListAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit) = lazy {
    listItemAdapter<T>(block)
}

fun <T : Any> listItemAdapter(block: GlideRecyclerAdapterConfig<T>.() -> Unit): ListItemAdapter<T> {
    val config = GlideRecyclerAdapterConfig<T>().apply(block)

    if (config.viewHolderFactories.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListItemAdapter(config, config.glideConfig)
}

class ListItemAdapter<T : Any>(
    override val config: RecyclerAdapterConfig<T>,
    override val glideConfig: GlideConfig<T>
) : ListAdapter<T, RecyclerView.ViewHolder>(config.itemDiffCallback), RecyclerViewAdapterBase<T>, GlideRecyclerAdapter<T> {

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

    override fun getItemBy(position: Int): T? = super.getItem(position)

    override fun getItem(position: Int): T? = super.getItem(position)

    override fun getItemCount(): Int = helper.getItemCount()

    override fun getItemViewType(position: Int): Int = helper.getItemViewType(position)

    override fun getItemId(index: Int): Long = helper.getItemId(index)

}
