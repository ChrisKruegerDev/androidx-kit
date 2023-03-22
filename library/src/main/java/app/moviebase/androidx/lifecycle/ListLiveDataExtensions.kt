package app.moviebase.androidx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import app.moviebase.androidx.widget.recyclerview.adapter.AbstractListRecyclerAdapter
import app.moviebase.androidx.widget.recyclerview.list.ListItemAdapter
import app.moviebase.androidx.lifecycle.bind

@Deprecated("Use bindListAdapter")
fun <T : Any> LiveData<out List<T>>.bindAdapterUpdate(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { if (adapter.data.isEmpty()) adapter.setData(it) else adapter.updateData(it) }
}

@Deprecated("Use bindListAdapter")
fun <T : Any> LiveData<out List<T>>.bindAdapter(owner: LifecycleOwner, adapter: AbstractListRecyclerAdapter<T>) {
    bind(owner) { adapter.setData(it) }
}

fun <T : Any> LiveData<out List<T>>.bindListAdapter(owner: LifecycleOwner, adapter: ListItemAdapter<T>) {
    bind(owner) { adapter.submitList(it) }
}
