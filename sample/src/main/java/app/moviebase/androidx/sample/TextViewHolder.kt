package app.moviebase.androidx.sample

import android.view.ViewGroup
import app.moviebase.androidx.sample.databinding.ItemTextBinding
import app.moviebase.androidx.widget.recyclerview.adapter.RecyclerViewAdapterBase
import app.moviebase.androidx.widget.recyclerview.viewholder.BindViewHolder

class TextViewHolder(
    adapter: RecyclerViewAdapterBase<TextItem>,
    parent: ViewGroup
) : BindViewHolder<TextItem>(adapter, parent, R.layout.item_text) {

    private val binding = ItemTextBinding.bind(itemView)

    override fun bindValue(value: TextItem?) {
        binding.textView.text = value?.text
    }
}
