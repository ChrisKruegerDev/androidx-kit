package com.placebox.androidx.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.placebox.androidx.widget.recyclerview.diff.DiffCallback
import java.util.*

typealias ListFilter<T> = (T) -> Boolean

abstract class AbstractListRecyclerAdapter<T : Any>(
        final override val config: RecyclerAdapterConfig<T>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerViewAdapterBase<T> {

    final override var data: MutableList<T> = ArrayList()
        private set

    final override val isDataValid: Boolean get() = true
    final override val selection by lazy {
        val instance = Selection(this)
        if (config.onSelection != null) instance.enabled = true
        instance
    }

    private var backedData: MutableList<T> = ArrayList()
    private val helper by lazy { RecyclerAdapterHelper<T>(this) }
    private var filter: ListFilter<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            helper.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            helper.onBindViewHolder(holder, position)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        helper.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = helper.getItemCount()

    override fun getItemViewType(position: Int): Int = helper.getItemViewType(position)

    override fun getItemId(index: Int): Long = helper.getItemId(index)

    override fun getItem(position: Int): T? =
            if (position < 0 || position >= data.size) null else data[position]

    fun remove(item: T) {
        val i = data.indexOf(item)
        if (i >= 0) removeAt(i)
    }

    fun removeAt(position: Int) {
        if (position >= 0 && position < data.size) {
            val newData = data.toMutableList()
            newData.removeAt(position)
            data = newData
            notifyItemRemoved(position)
        }
    }

    fun swapPosition(fromPosition: Int, toPosition: Int) {
        if (fromPosition == toPosition) return

        Collections.swap(data, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun setData(newList: List<T>?) {
        backedData = newList?.let{ ArrayList(it) } ?: ArrayList()
        updateData(backedData)
        // update hole list to prevent wrong position in UI
        notifyDataSetChanged()
    }

    fun updateData(newList: List<T>) {
        val predicate = filter
        val list = if (predicate == null)
            ArrayList(newList)
        else
            newList.filter(predicate).toMutableList()

        val callback = DiffCallback(data, list)
        val result = DiffUtil.calculateDiff(callback)
        data = list
        result.dispatchUpdatesTo(this)
    }

    fun addData(furtherMovieData: List<T>) {
        if (furtherMovieData.isEmpty()) return
        backedData.addAll(furtherMovieData)
        updateData(backedData)
    }

    fun setFilter(listFilter: ListFilter<T>?) {
        if (filter === listFilter) return
        filter = listFilter
        updateData(backedData)
    }

}
