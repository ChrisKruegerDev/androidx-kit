package app.moviebase.androidx.widget.recyclerview.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class LayoutViewHolder(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {

    val context: Context get() = containerView.context

    constructor(
        parent: ViewGroup,
        @LayoutRes resource: Int
    ) : this(LayoutInflater.from(parent.context).inflate(resource, parent, false))
}
