package com.placebox.androidx.sample

import android.view.ViewGroup
import com.placebox.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import com.placebox.androidx.widget.recyclerview.viewholder.BindViewHolder

class HeaderViewHolder(
    adapter: RecyclerViewAdapterBase<TextItem>,
    parent: ViewGroup
) : BindViewHolder<TextItem>(adapter, parent, R.layout.item_header_text) {

    override fun bindValue(value: TextItem?) {

    }

}
