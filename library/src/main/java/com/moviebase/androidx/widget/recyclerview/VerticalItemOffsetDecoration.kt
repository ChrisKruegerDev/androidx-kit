package com.moviebase.androidx.widget.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

object OffsetPosition {
    const val TOP = 0
    const val BOTTOM = 1
}

class VerticalItemOffsetDecoration(
    private val offsetInPixel: Int,
    private val includesFirstItem: Boolean = false,
    private val offsetPosition: Int = OffsetPosition.BOTTOM
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (includesFirstItem || parent.getChildAdapterPosition(view) != 0) {
            if (offsetPosition == OffsetPosition.BOTTOM)
                outRect.bottom = offsetInPixel
            else
                outRect.top = offsetInPixel
        }
    }
}
