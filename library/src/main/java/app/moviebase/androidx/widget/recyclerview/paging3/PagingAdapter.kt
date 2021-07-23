package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.GlideItemAdapter
import app.moviebase.androidx.widget.recyclerview.adapter.ItemAdapter
import app.moviebase.androidx.widget.recyclerview.adapter.OnValueHashCode
import app.moviebase.androidx.widget.recyclerview.adapter.ViewType
import app.moviebase.androidx.widget.recyclerview.adapter.list.GlideConfig
import app.moviebase.androidx.widget.recyclerview.viewholder.ImageViewHolder
import app.moviebase.androidx.widget.recyclerview.viewholder.ItemViewHolder
import app.moviebase.androidx.widget.recyclerview.viewholder.Recyclable
import com.bumptech.glide.RequestBuilder

fun <T : Any> lazyPagingAdapter(block: PagingAdapterConfig<T>.() -> Unit) = lazy { pagingAdapter(block) }

fun <T : Any> pagingAdapter(block: PagingAdapterConfig<T>.() -> Unit): PagingAdapter<T> {
    val config = PagingAdapterConfig<T>()
    config.onItemId = OnValueHashCode()
    config.apply(block)

    return PagingAdapter(config)
}

class PagingAdapter<T : Any>(
    override val config: PagingAdapterConfig<T>,
) : PagingDataAdapter<T, ItemViewHolder<T>>(config.diffCallback), GlideItemAdapter<T>, ItemAdapter<T> {

    override val glideConfig: GlideConfig<T> get() = config.glideConfig

    fun withLoadStateFooter(): RecyclerView.Adapter<*> {
        val viewHolderFactory = config.loadStateViewHolder ?: return this
        return withLoadStateFooter(
            footer = LoadStateAdapter(this, viewHolderFactory)
        )
    }

    // TODO: 20.07.21 check how many items to load?
    override fun getPreloadItems(position: Int): List<T> {
        val itemCount = itemCount
        return if (position < 0 || itemCount == 0 || position >= itemCount)
            emptyList()
        else {
            val elements = getItem(position + 1) ?: return emptyList()
            mutableListOf(elements)
        }
    }

    override fun getPreloadRequestBuilder(item: T): RequestBuilder<*>? {
        return glideConfig.loader?.preload(item, null)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ItemViewHolder<T>, position: Int) {
        val value: T? = getItem(position)
        holder.bindTo(value, position)

        if (holder is ImageViewHolder) {
            val imageView = holder.imageView

            val tag = imageView.tag
            val newTag = glideConfig.loader?.getTag(value)
            // don't load it again if it has the same tag
            if (tag != null && newTag == tag) return

            val requestBuilder = glideConfig.loader?.load(value, holder)
            requestBuilder?.into(imageView)?.waitForLayout()
            imageView.tag = newTag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> {
        val factory = config.viewHolders[viewType]
            ?: throw NoSuchElementException("factory for view type '$viewType' not available")

        val holder = factory.create(this, parent)
        if (holder is ImageViewHolder) preloadProvider.setView(holder.imageView)
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        val onViewType = config.onViewType ?: return ViewType.VIEW_TYPE_DEFAULT
        val item = getItem(position)
        return onViewType(item)
    }

    override fun onViewRecycled(holder: ItemViewHolder<T>) {
        if (holder is Recyclable) holder.recycle()
        if (holder is ImageViewHolder) {
            val imageView = holder.imageView
            glideConfig.loader?.clearGlide(imageView)
            imageView.tag = null
        }
    }
}
