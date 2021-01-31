package com.moviebase.androidx.widget.recyclerview.drag

import androidx.recyclerview.widget.ItemTouchHelper

fun ItemTouchHelper(apply: LambdaItemTouchHelperAdapter.() -> Unit): ItemTouchHelper {
    val adapter = LambdaItemTouchHelperAdapter().apply(apply)
    return ItemTouchHelper(SimpleItemTouchHelperCallback(adapter))
}


class LambdaItemTouchHelperAdapter : ItemTouchHelperAdapter {

    lateinit var itemMove: (from: Int, to: Int) -> Unit
    lateinit var itemDismiss: (Int) -> Unit

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        itemMove(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        itemDismiss(position)
    }
}

