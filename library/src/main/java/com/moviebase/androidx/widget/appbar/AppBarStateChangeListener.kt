package com.moviebase.androidx.widget.appbar

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs

inline fun AppBarLayout.doOnStateChanged(threshold: Int, crossinline onStateChanged: (AppBarStateChangeListener.State) -> Unit) {
    val listener = object : AppBarStateChangeListener(threshold) {
        override fun onStateChanged(state: State) {
            onStateChanged(state)
        }
    }
    addOnOffsetChangedListener(listener)
}

abstract class AppBarStateChangeListener(private val threshold: Int) : OnOffsetChangedListener {

    private var currentState = State.IDLE
    private var totalScrollRange = -1

    override fun onOffsetChanged(layout: AppBarLayout, i: Int) {
        if (totalScrollRange == -1)
            totalScrollRange = layout.totalScrollRange

        currentState = when {
            i == 0                                 -> {
                if (currentState != State.EXPANDED) onStateChanged(State.EXPANDED)
                State.EXPANDED
            }
            abs(i) >= totalScrollRange + threshold -> {
                if (currentState != State.COLLAPSED) onStateChanged(State.COLLAPSED)
                State.COLLAPSED
            }
            else                                   -> {
                if (currentState != State.IDLE) onStateChanged(State.IDLE)
                State.IDLE
            }
        }
    }

    abstract fun onStateChanged(state: State)

    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

}
