package app.moviebase.androidx.widget.appbar

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs



abstract class AppBarStateChangeListener(private val threshold: Int) : OnOffsetChangedListener {

    private var currentState = State.IDLE
    private var totalScrollRange = -1

    init {
        require(threshold >= 0) { "threshold must be 0 or higher: $threshold" }
    }

    override fun onOffsetChanged(layout: AppBarLayout, i: Int) {
        if (totalScrollRange == -1)
            totalScrollRange = layout.totalScrollRange

        currentState = when {
            i == 0                                 -> {
                if (currentState != State.EXPANDED) onStateChanged(layout, State.EXPANDED)
                State.EXPANDED
            }
            abs(i) >= totalScrollRange - threshold -> {
                if (currentState != State.COLLAPSED) onStateChanged(layout, State.COLLAPSED)
                State.COLLAPSED
            }
            else                                   -> {
                if (currentState != State.IDLE) onStateChanged(layout, State.IDLE)
                State.IDLE
            }
        }
    }

    abstract fun onStateChanged(layout: AppBarLayout, state: State)

    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE;

        val isCollapsed: Boolean get() = this == COLLAPSED
    }

}
