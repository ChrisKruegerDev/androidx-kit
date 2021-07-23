package app.moviebase.androidx.widget.recyclerview.adapter.list

import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.adapter.GlideItemAdapter
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader

fun <T : Any> RecyclerView.addOnViewPreload(adapter: GlideItemAdapter<T>, maxPreload: Int) {
    val viewPreloader = glideRecyclerViewPreloader(adapter, maxPreload)
    addOnScrollListener(viewPreloader)
}

private fun <T : Any> glideRecyclerViewPreloader(adapter: GlideItemAdapter<T>, maxPreload: Int) =
    RecyclerViewPreloader(adapter.requests, adapter, adapter.preloadProvider, maxPreload)
