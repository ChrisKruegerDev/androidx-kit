package com.moviebase.androidx.widget.appbar

import android.view.View
import kotlin.math.max

class AlphaAppBarStateChangeListener(private val view: View) : PercentAppBarStateChangeListener() {

    override fun onChanged(percentage: Int) {
        val scale = 1f - (percentage.toFloat() * 4) / 100
        view.alpha = max(scale, 0.2f)
    }

}

