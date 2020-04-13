package com.placebox.androidx.widget.recyclerview.adapter

private val classViewTypeFactory: (Any?) -> Int = { it?.javaClass?.hashCode() ?: ViewType.VIEW_TYPE_DEFAULT }

object ViewType {
    const val VIEW_TYPE_DEFAULT = 0
    const val VIEW_TYPE_EMPTY = 1
    const val VIEW_TYPE_HEADER = 1000000
    const val VIEW_TYPE_FOOTER = 1000001
}

object ItemId {
    val ID_HEADER = "viewHolderHeader".hashCode().toLong()
    val ID_FOOTER = "viewHolderFooter".hashCode().toLong()
}
