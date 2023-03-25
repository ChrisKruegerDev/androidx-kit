package app.moviebase.androidx.widget.recyclerview.list

fun <T : Any> lazyListAdapter(block: ListItemAdapterConfig<T>.() -> Unit) = lazy {
    listItemAdapter<T>(block)
}

fun <T : Any> listItemAdapter(block: ListItemAdapterConfig<T>.() -> Unit): ListItemAdapter<T> {
    val config = ListItemAdapterConfig<T>()
    config.apply(block)

    if (config.viewHolders.isEmpty())
        throw IllegalStateException("no view holder factories available")

    return ListItemAdapter(config)
}
