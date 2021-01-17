package com.moviebase.androidx.sample

import android.view.ViewGroup
import com.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import com.moviebase.androidx.widget.recyclerview.viewholder.BindViewHolder
import kotlinx.android.synthetic.main.item_text.*

class TextViewHolder(
    adapter: RecyclerViewAdapterBase<TextItem>,
    parent: ViewGroup
) : BindViewHolder<TextItem>(adapter, parent, R.layout.item_text) {

    override fun bindValue(value: TextItem?) {
        textView.text = value?.text
    }
}
