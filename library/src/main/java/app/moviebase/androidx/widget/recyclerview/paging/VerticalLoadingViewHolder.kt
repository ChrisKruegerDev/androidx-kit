package app.moviebase.androidx.widget.recyclerview.paging

import android.view.ViewGroup
import androidx.core.view.isVisible
import app.moviebase.androidx.R
import app.moviebase.androidx.databinding.ListItemLoadingVerticalBinding
import app.moviebase.androidx.widget.recyclerview.viewholder.BindValue
import app.moviebase.androidx.widget.recyclerview.viewholder.LayoutViewHolder

class VerticalLoadingViewHolder(
    parent: ViewGroup
) : LayoutViewHolder(parent, R.layout.list_item_loading_vertical), BindValue<NetworkState> {

    private val binding = ListItemLoadingVerticalBinding.bind(itemView)

    override fun bind(value: NetworkState?) {
        binding.progressBar.isVisible = value?.status == Status.RUNNING
    }
}
