package app.moviebase.androidx.widget.appbar

import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import app.moviebase.androidx.content.toPx

class ElevationAppBarStateChangeListener(private val elevationDp: Int) : AppBarStateChangeListener(0) {

    override fun onStateChanged(layout: AppBarLayout, state: State) {
        val elevation = if (state.isCollapsed) elevationDp.toPx().toFloat() else 0f
        ViewCompat.setElevation(layout, elevation)
    }
}
