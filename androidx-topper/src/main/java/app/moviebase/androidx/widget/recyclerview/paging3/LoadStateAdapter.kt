package app.moviebase.androidx.widget.recyclerview.paging3

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadStateAdapter(
    private val adapter: PagingAdapter<*>,
    private val loadStateViewHolderFactory: LoadStateViewHolderBuilder
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        return loadStateViewHolderFactory.create(adapter, parent)
    }
}
