package com.moviebase.androidx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.moviebase.androidx.widget.recyclerview.adapter.AbstractListRecyclerAdapter
import com.moviebase.ktx.lifecycle.bind

fun <T : Any> LiveData<out List<T>>.bindAdapterUpdate(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { if (adapter.data.isEmpty()) adapter.setData(it) else adapter.updateData(it) }
}

fun <T : Any> LiveData<out List<T>>.bindAdapter(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { adapter.setData(it) }
}

