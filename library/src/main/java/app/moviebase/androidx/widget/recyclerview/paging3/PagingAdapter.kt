package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.OnValueHashCode
import app.moviebase.androidx.widget.recyclerview.adapter.ViewType
import app.moviebase.androidx.widget.recyclerview.glide.GlideAdapterHelper
import app.moviebase.androidx.widget.recyclerview.glide.GlideConfig
import app.moviebase.androidx.widget.recyclerview.glide.GlideItemAdapter
import app.moviebase.androidx.widget.recyclerview.viewholder.ItemViewHolder
import app.moviebase.androidx.widget.recyclerview.viewholder.Recyclable

fun <T : Any> lazyPagingAdapter(block: PagingAdapterConfig<T>.() -> Unit) = lazy { pagingAdapter(block) }

fun <T : Any> pagingAdapter(block: PagingAdapterConfig<T>.() -> Unit): PagingAdapter<T> {
    val config = PagingAdapterConfig<T>()
    config.onItemId = OnValueHashCode()
    config.apply(block)

    return PagingAdapter(config)
}

class PagingAdapter<T : Any>(
    override val config: PagingAdapterConfig<T>,
) : PagingDataAdapter<T, ItemViewHolder<T>>(config.diffCallback), GlideItemAdapter<T> {

    override val glideConfig: GlideConfig<T> get() = config.glideConfig

    fun withLoadStateFooter(): RecyclerView.Adapter<*> {
        val viewHolderFactory = config.loadStateViewHolder ?: return this
        return withLoadStateFooter(
            footer = LoadStateAdapter(this, viewHolderFactory)
        )
    }

    override fun getItemBy(position: Int): T? = getItem(position)

    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        val value: T? = getItem(position)
        holder.bindTo(value, position)
        GlideAdapterHelper.bindImageView(glideConfig, value, holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> {
        val factory = config.viewHolders[viewType]
            ?: throw NoSuchElementException("factory for view type '$viewType' not available")

        val holder = factory.create(this, parent)
        GlideAdapterHelper.updateView(glideConfig, holder)
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        val onViewType = config.onViewType ?: return ViewType.VIEW_TYPE_DEFAULT
        val item = getItem(position)
        return onViewType.getViewType(item)
    }

    override fun onViewRecycled(holder: ItemViewHolder<T>) {
        if (holder is Recyclable) holder.recycle()
        GlideAdapterHelper.clearView(glideConfig, holder)
    }
}
