package app.moviebase.androidx.widget.recyclerview.paging3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

abstract class LoadStateViewHolder(
    parent: ViewGroup,
    @LayoutRes resource: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(resource, parent, false)) {

    private val context: Context get() = itemView.context

    abstract fun bindTo(loadState: LoadState)

}
