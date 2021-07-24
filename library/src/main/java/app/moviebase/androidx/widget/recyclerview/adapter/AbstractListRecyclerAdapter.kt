package app.moviebase.androidx.widget.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.widget.recyclerview.diff.DiffCallback
import java.util.*

@Deprecated("only used for AbstractListRecyclerAdapter")
typealias ListFilter<T> = (T) -> Boolean

@Deprecated(
    "use the native solution with ListAdapter",
    ReplaceWith("ListAdapter", "app.moviebase.androidx.widget.recyclerview.adapter.ListAdapter")
)
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

    init {
        setHasStableIds(config.onItemId != null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        helper.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        helper.onBindViewHolder(holder, position)

    /**
     * If payloads is empty then the regular bind view holder will be called.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        helper.onBindViewHolder(holder, position, if(payloads.isEmpty()) null else payloads)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        helper.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = helper.getItemCount()

    override fun getItemViewType(position: Int): Int = helper.getItemViewType(position)

    override fun getItemId(index: Int): Long = helper.getItemId(index)

    override fun getItem(position: Int): T? = data.getOrNull(position)

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
        backedData = newList?.let { ArrayList(it) } ?: ArrayList()
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

        val callback = DiffCallback(data, list, config.itemDiffCallback)
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
