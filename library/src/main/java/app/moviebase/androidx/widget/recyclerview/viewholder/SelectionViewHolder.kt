package app.moviebase.androidx.widget.recyclerview.viewholder

import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import kotlinx.android.extensions.LayoutContainer

interface SelectionViewHolder : LayoutContainer {
    fun applyAnimation(adapter: RecyclerViewAdapterBase<*>, position: Int)
}
