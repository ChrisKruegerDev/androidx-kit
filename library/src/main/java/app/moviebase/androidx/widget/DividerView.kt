package app.moviebase.androidx.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import app.moviebase.androidx.R


const val ORIENTATION_HORIZONTAL = 0
const val ORIENTATION_VERTICAL = 1

class DividerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private var orientation: Int = 0

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.DividerView, 0, 0)
        try {
            orientation = a.getInt(R.styleable.DividerView_orientation, ORIENTATION_VERTICAL)
            paint.strokeWidth = a.getDimensionPixelSize(R.styleable.DividerView_strokeWidth, 2).toFloat()
            paint.color = a.getColor(R.styleable.DividerView_strokeColor, Color.DKGRAY)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (orientation == ORIENTATION_HORIZONTAL) {
            val center = height * .5f
            canvas?.drawLine(0f, center, width.toFloat(), center, paint)
        } else {
            val center = width * .5f
            canvas?.drawLine(center, 0f, center, height.toFloat(), paint)
        }
    }

}
