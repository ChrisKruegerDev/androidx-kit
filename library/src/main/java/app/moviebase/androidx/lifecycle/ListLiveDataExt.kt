package app.moviebase.androidx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import app.moviebase.androidx.widget.recyclerview.adapter.AbstractListRecyclerAdapter
import app.moviebase.ktx.lifecycle.bind

fun <T : Any> LiveData<out List<T>>.bindAdapterUpdate(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { if (adapter.data.isEmpty()) adapter.setData(it) else adapter.updateData(it) }
}

fun <T : Any> LiveData<out List<T>>.bindAdapter(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { adapter.setData(it) }
}

