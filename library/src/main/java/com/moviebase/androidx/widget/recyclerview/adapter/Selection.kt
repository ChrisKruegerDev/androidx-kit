package com.moviebase.androidx.widget.recyclerview.adapter

import android.util.SparseBooleanArray

class Selection(private val adapter: RecyclerViewAdapterBase<*>) {

    var enabled = false

    // array used to perform multiple animation at once
    val selectedItems = SparseBooleanArray()
    val animationItemsIndex = SparseBooleanArray()
    var reverseAllAnimations = false
        private set

    // order is used to animate only the selected row
    var currentSelectedIndex = -1
        private set

    fun setSelection(pos: Int) {
        if (selectedItems.size() == 1 && selectedItems.get(pos)) {
            return
        }

        clearSelections()
        toggleSelection(pos)
    }

    fun toggleSelection(pos: Int) {
        currentSelectedIndex = pos
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos)
            animationItemsIndex.delete(pos)
        } else {
            selectedItems.put(pos, true)
            animationItemsIndex.put(pos, true)
        }

        adapter.notifyItemChanged(if (adapter.useHeader()) pos + 1 else pos)
    }

    fun deleteSelection(pos: Int) {
        if (!selectedItems.get(pos, false)) {
            return
        }

        selectedItems.delete(pos)
        animationItemsIndex.delete(pos)

        adapter.notifyItemChanged(if (adapter.useHeader()) pos + 1 else pos)
    }


    fun clearSelections() {
        reverseAllAnimations = true
        selectedItems.clear()

        adapter.notifyDataSetChanged()
    }

    fun resetAnimationIndex() {
        reverseAllAnimations = false
        animationItemsIndex.clear()
    }

    fun getSelectedItemCount(): Int {
        return selectedItems.size()
    }

    fun getSelectedItemsIntArray(): IntArray {
        val arr = IntArray(selectedItems.size())
        for (i in 0 until selectedItems.size()) {
            arr[i] = selectedItems.keyAt(i)
        }
        return arr
    }

    fun resetCurrentIndex() {
        currentSelectedIndex = -1
    }

}
