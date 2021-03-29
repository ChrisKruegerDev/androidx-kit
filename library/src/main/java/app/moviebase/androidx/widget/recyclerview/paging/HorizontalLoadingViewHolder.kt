package app.moviebase.androidx.widget.recyclerview.paging

import android.view.ViewGroup
import androidx.core.view.isVisible
import app.moviebase.androidx.R
import app.moviebase.androidx.widget.recyclerview.viewholder.BindValue
import app.moviebase.androidx.widget.recyclerview.viewholder.LayoutViewHolder
import kotlinx.android.synthetic.main.list_item_loading_horizontal.*

class HorizontalLoadingViewHolder(
        parent: ViewGroup
) : LayoutViewHolder(parent, R.layout.list_item_loading_horizontal), BindValue<NetworkState> {

    override fun bind(value: NetworkState?) {
        progressBar.isVisible = value?.status == Status.RUNNING
    }

}
