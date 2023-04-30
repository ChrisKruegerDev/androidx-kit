package app.moviebase.androidx.widget.appbar

import com.google.android.material.appbar.AppBarLayout

inline fun AppBarLayout.doOnStateChanged(threshold: Int, crossinline onStateChanged: (AppBarStateChangeListener.State) -> Unit) {
    val listener = object : AppBarStateChangeListener(threshold) {
        override fun onStateChanged(layout: AppBarLayout, state: State) {
            onStateChanged(state)
        }
    }
    addOnOffsetChangedListener(listener)
}

fun AppBarLayout.addElevationOnChange(dp: Int = 8) = addOnOffsetChangedListener(ElevationAppBarStateChangeListener(dp))
