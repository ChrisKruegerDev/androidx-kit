package app.moviebase.androidx.widget.recyclerview.viewholder

import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase

interface SelectionViewHolder {
    fun applyAnimation(adapter: RecyclerViewAdapterBase<*>, position: Int)
}
