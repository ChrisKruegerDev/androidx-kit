package com.moviebase.androidx.widget.recyclerview.viewholder

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase

open class EmptyBindViewHolder<T: Any>(
    adapter: RecyclerViewAdapterBase<T>,
    parent: ViewGroup,
    @LayoutRes resource: Int
): BindViewHolder<T>(adapter, parent, resource) {

    override fun bindValue(value: T?) {
    }

}
