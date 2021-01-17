package com.moviebase.androidx.view

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

class OtherAlphaOnTouchListener(
    private val selectAlpha: Float = 0.3f,
    private val defaultAlpha: Float = 1.0f,
    private val view: View,
    private val otherDefaultAlpha: Float = 1.0f,
    private val otherViews: List<View> = emptyList()
) : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v ?: return false

        when (event?.action) {
            MotionEvent.ACTION_DOWN                          -> {
                view.alpha = selectAlpha
                otherViews.forEach { it.alpha = selectAlpha }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                view.animate().alpha(defaultAlpha).setDuration(250).start()
                otherViews.forEach {
                    it.animate().alpha(otherDefaultAlpha).setDuration(250).start()
                }
            }
        }
        return false
    }

}
