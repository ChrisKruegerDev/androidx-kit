package com.moviebase.androidx.widget.appbar

import androidx.appcompat.app.AppCompatActivity

class TitleAppBarChangeListener(
    threshold: Int,
    private val activity: AppCompatActivity,
    private val title: String
) : AppBarStateChangeListener(threshold) {

    override fun onStateChanged(state: State) {
        activity.supportActionBar?.title = if (state == State.COLLAPSED) title else null
    }

}
