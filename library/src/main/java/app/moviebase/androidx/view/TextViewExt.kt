package app.moviebase.androidx.view

import android.graphics.BlurMaskFilter
import android.text.Layout
import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible

fun TextView.setDrawableEnd(resId: Int?) {
    setCompoundDrawablesWithIntrinsicBounds(0, 0, resId ?: 0, 0)
}

fun TextView.setDrawableStart(resId: Int?) {
    setCompoundDrawablesWithIntrinsicBounds(resId ?: 0, 0,  0, 0)
}

fun TextView.updateText(value: CharSequence?) {
    if (text?.toString() == value?.toString()) return
    text = value
}

val TextView.isEllipsized: Boolean
    get() {
        val layout: Layout = layout ?: return false
        val lines: Int = layout.lineCount

        return lines > 0 && layout.getEllipsisCount(lines - 1) > 0
    }

var TextView.textOrInvisible: CharSequence?
    get() = text
    set(value) {
        isInvisible = value.isNullOrBlank()
        text = value
    }

var TextView.textOrGone: CharSequence?
    get() = text
    set(value) {
        text = value
        isGone = value.isNullOrBlank()
    }


/**
 * Remove layer type for correct displaying of TextView
 */
fun TextView.clearBlurMask() {
    setLayerType(View.LAYER_TYPE_NONE, null)
    paint.maskFilter = null
}

/**
 * Needs layer type software for correct blurring.
 * Can not display if text lines are too long.
 */
fun TextView.enableBlurMask() {
    setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    val radius = textSize / 4
    paint.maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
}
