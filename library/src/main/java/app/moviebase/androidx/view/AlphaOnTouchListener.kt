package app.moviebase.androidx.view

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class AlphaOnTouchListener(
    private val selectAlpha: Float = 0.3f,
    private val defaultAlpha: Float = 1.0f
) : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v ?: return false

        when (event?.action) {
            MotionEvent.ACTION_DOWN                          -> v.alpha = selectAlpha
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> v.animate().alpha(defaultAlpha).setDuration(
                250
            ).start()
        }
        return false
    }

}
