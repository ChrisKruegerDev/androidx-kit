package com.moviebase.androidx.widget.recyclerview.paging

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.moviebase.androidx.R
import com.moviebase.androidx.widget.recyclerview.viewholder.BindValue
import com.moviebase.androidx.widget.recyclerview.viewholder.LayoutViewHolder
import kotlinx.android.synthetic.main.list_item_loading_vertical.*

class VerticalLoadingViewHolder(
        parent: ViewGroup
) : LayoutViewHolder(parent, R.layout.list_item_loading_vertical), BindValue<NetworkState> {

    override fun bind(value: NetworkState?) {
        progressBar.isVisible = value?.status == Status.RUNNING
    }

}
