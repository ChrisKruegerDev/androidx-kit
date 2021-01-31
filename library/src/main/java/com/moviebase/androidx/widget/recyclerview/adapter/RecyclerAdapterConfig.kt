package com.moviebase.androidx.widget.recyclerview.adapter


import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.moviebase.androidx.widget.recyclerview.adapter.ViewType.VIEW_TYPE_DEFAULT
import com.moviebase.androidx.widget.recyclerview.viewholder.BindViewHolder
import kotlin.reflect.KClass

typealias OnViewType = (Any?) -> Int
typealias OnItemId<T> = (T) -> Long
typealias OnClick<T> = (T, RecyclerView.ViewHolder) -> Unit
typealias OnLongClick<T> = (T) -> Unit
typealias OnSelection<T> = (adapter: RecyclerViewAdapterBase<T>, position: Int, value: T) -> Unit
typealias ViewHolderFactory<T> = (adapter: RecyclerViewAdapterBase<T>, parent: ViewGroup) -> BindViewHolder<T>?

fun <T> OnClick<T>.and(then: OnClick<T>): OnClick<T> = { value, viewHolder ->
    this(value, viewHolder)
    then(value, viewHolder)
}

private val classViewTypeFactory: (Any?) -> Int = { it?.javaClass?.hashCode() ?: VIEW_TYPE_DEFAULT }

open class RecyclerAdapterConfig<T: Any> {

    var orientation = LinearLayout.VERTICAL
    var onClick: OnClick<T>? = null
    var onLongClick: OnLongClick<T>? = null
    var onSelection: OnSelection<T>? = null
    val viewHolderFactories = mutableMapOf<Int, ViewHolderFactory<T>>()
    var headerViewHolderFactory: ViewHolderFactory<T>? = null
    var footerViewHolderFactory: ViewHolderFactory<T>? = null
    var onViewType: OnViewType? = null
    var onItemId: OnItemId<T>? = null

    fun onViewType(onViewType: OnViewType) {
        this.onViewType = onViewType
    }

    fun onSelection(onSelection: OnSelection<T>) {
        this.onSelection = onSelection
    }

    fun onClick(onClick: (T) -> Unit) {
        this.onClick = { value, _ -> onClick(value) }
    }

    fun onItemId(onItemId: OnItemId<T>) {
        this.onItemId = onItemId
    }

    fun onLongClick(onLongClick: OnLongClick<T>) {
        this.onLongClick = onLongClick
    }

    fun viewHolder(factory: ViewHolderFactory<T>) {
        viewHolderFactories[VIEW_TYPE_DEFAULT] = factory
    }

    fun viewHolder(viewType: Int, factory: ViewHolderFactory<T>) {
        viewHolderFactories[viewType] = factory
    }

    fun viewHolder(viewType: KClass<out T>, factory: ViewHolderFactory<T>) {
        onViewType = classViewTypeFactory
        viewHolderFactories[viewType.java.hashCode()] = factory
    }

    fun headerViewHolder(factory: ViewHolderFactory<T>) {
        headerViewHolderFactory = factory
    }

    fun footerViewHolder(factory: ViewHolderFactory<T>) {
        footerViewHolderFactory = factory
    }
}

