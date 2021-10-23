package app.moviebase.androidx.widget.recyclerview.adapter

object ViewType {
    const val VIEW_TYPE_DEFAULT = 0
    const val VIEW_TYPE_HEADER = 1
    const val VIEW_TYPE_FOOTER = 2
}

object ItemId {
    val ID_HEADER = "viewHolderHeader".hashCode().toLong()
    val ID_FOOTER = "viewHolderFooter".hashCode().toLong()
}
