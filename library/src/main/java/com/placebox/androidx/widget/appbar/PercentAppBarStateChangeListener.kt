package com.placebox.androidx.widget.appbar

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

abstract class PercentAppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {

    private var totalScrollRange = 0

    override fun onOffsetChanged(layout: AppBarLayout, i: Int) {
        if (totalScrollRange == 0)
            totalScrollRange = layout.totalScrollRange

        if (totalScrollRange == 0) return

        val percentage = abs(i) * 100 / totalScrollRange
        onChanged(percentage)
    }

    abstract fun onChanged(percentage: Int)

}
