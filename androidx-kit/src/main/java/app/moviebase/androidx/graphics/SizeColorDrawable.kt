package app.moviebase.androidx.graphics

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorInt

class SizeColorDrawable(
    @ColorInt color: Int,
    private val width: Int,
    private val height: Int
) : ColorDrawable(color) {

    override fun getIntrinsicWidth(): Int = width

    override fun getIntrinsicHeight(): Int = height

}
