package com.placebox.androidx.widget.recyclerview.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is Diffable)
            oldItem.isItemTheSame(newItem)
        else
            oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is Diffable)
            oldItem.isContentTheSame(newItem)
        else
            oldItem == newItem
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return if (oldItem is Diffable)
            oldItem.isContentTheSame(newItem)
        else
            null
    }
}
