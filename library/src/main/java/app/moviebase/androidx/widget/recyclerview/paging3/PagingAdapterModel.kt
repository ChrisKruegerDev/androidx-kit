package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup

fun interface LoadStateViewHolderBuilder {
    fun create(adapter: PagingAdapter<*>, parent: ViewGroup): LoadStateViewHolder
}

fun <T : Any> lazyPagingAdapter(block: PagingAdapterConfig<T>.() -> Unit) = lazy { pagingAdapter(block) }

fun <T : Any> pagingAdapter(block: PagingAdapterConfig<T>.() -> Unit): PagingAdapter<T> {
    val config = PagingAdapterConfig<T>()
    config.apply(block)

    if (config.viewHolders.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return PagingAdapter(config)
}
