package com.moviebase.androidx.widget.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil


class DiffCallback<T : Any>(
        private val oldItems: List<T>,
        private val newItems: List<T>,
        private val itemDiffCallback: ItemDiffCallback<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return itemDiffCallback.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return itemDiffCallback.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return itemDiffCallback.getChangePayload(oldItem, newItem)
    }

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

}
