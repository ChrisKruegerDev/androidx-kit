package com.moviebase.androidx.widget.recyclerview.adapter.list

import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader

inline fun <reified T : Any> GlideRecyclerViewPreloader(adapter: GlideRecyclerAdapter<T>, maxPreload: Int) =
    RecyclerViewPreloader(
        adapter.requests,
        adapter,
        adapter.preloadProvider,
        maxPreload
    )
