package app.moviebase.androidx.widget.recyclerview.adapter.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader

fun <T : Any> RecyclerView.addOnViewPreload(adapter: GlideRecyclerAdapter<T>, maxPreload: Int) {
    addOnScrollListener(glideRecyclerViewPreloader(adapter, maxPreload))
}

fun <T : Any> glideRecyclerViewPreloader(adapter: GlideRecyclerAdapter<T>, maxPreload: Int) =
    RecyclerViewPreloader(adapter.requests, adapter, adapter.preloadProvider, maxPreload)
