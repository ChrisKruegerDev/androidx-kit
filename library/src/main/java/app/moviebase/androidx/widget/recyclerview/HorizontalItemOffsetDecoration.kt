package app.moviebase.androidx.widget.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.content.px

class HorizontalItemOffsetDecoration(
    offsetDp: Int,
) : RecyclerView.ItemDecoration() {

    private val offset = offsetDp.px

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = offset
    }
}
