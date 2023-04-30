package app.moviebase.androidx.widget.appbar

import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

class TitleAppBarChangeListener(
    threshold: Int,
    private val toolbar: Toolbar,
    private val title: String
) : AppBarStateChangeListener(threshold) {

    override fun onStateChanged(layout: AppBarLayout, state: State) {
        toolbar.title = if (state.isCollapsed) title else null
    }

}
