package app.moviebase.androidx.widget.recyclerview.paging3

import android.content.Context
import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

abstract class LoadStateViewHolder(
    private val adapter: PagingAdapter<*>,
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {

    private val context: Context get() = containerView.context

    abstract fun bindTo(loadState: LoadState)

}
