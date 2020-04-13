package com.placebox.androidx.widget.recyclerview.viewholder

import com.placebox.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import kotlinx.android.extensions.LayoutContainer

interface SelectionViewHolder : LayoutContainer {
    fun applyAnimation(adapter: RecyclerViewAdapterBase<*>, position: Int)
}
