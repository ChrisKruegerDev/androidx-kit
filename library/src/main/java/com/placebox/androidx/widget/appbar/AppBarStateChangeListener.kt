package com.placebox.androidx.widget.appbar

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs

abstract class AppBarStateChangeListener(private val threshold: Int) : OnOffsetChangedListener {

    private var mCurrentState = State.IDLE
    private var totalScrollRange = -1

    override fun onOffsetChanged(layout: AppBarLayout, i: Int) {
        if (totalScrollRange == -1)
            totalScrollRange = layout.totalScrollRange

        mCurrentState = when {
            i == 0                                 -> {
                if (mCurrentState != State.EXPANDED) onStateChanged(layout, State.EXPANDED)
                State.EXPANDED
            }
            abs(i) >= totalScrollRange + threshold -> {
                if (mCurrentState != State.COLLAPSED) onStateChanged(layout, State.COLLAPSED)
                State.COLLAPSED
            }
            else                                   -> {
                if (mCurrentState != State.IDLE) onStateChanged(layout, State.IDLE)
                State.IDLE
            }
        }
    }

    abstract fun onStateChanged(
        layout: AppBarLayout?,
        state: State?
    )

    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

}