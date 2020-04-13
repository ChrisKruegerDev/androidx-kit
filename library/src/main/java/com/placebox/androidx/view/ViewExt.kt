package com.placebox.androidx.view

import android.view.View
import androidx.annotation.IdRes


inline fun <reified T : View> View.viewId(@IdRes id: Int): Lazy<T> = lazy { findViewById<T>(id) }

fun View.updateEnabled(enable: Boolean, alphaValue: Double = .4) {
    isEnabled = enable

    if (enable) {
        alpha = 1f
        visibility = View.VISIBLE
    } else {
        alpha = alphaValue.toFloat()
    }
}
