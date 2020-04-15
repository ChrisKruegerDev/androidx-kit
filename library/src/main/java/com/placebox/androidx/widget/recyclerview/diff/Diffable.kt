package com.placebox.androidx.widget.recyclerview.diff

interface Diffable {

    fun isItemTheSame(other: Any): Boolean
    fun isContentTheSame(other: Any): Boolean
    fun getChangePayload(other: Any): Any? = null

}
