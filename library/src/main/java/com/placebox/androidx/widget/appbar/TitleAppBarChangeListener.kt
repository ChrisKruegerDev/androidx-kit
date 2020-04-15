package com.placebox.androidx.widget.appbar

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout

class TitleAppBarChangeListener(
    private val activity: AppCompatActivity,
    threshold: Int,
    private val title: String
) : AppBarStateChangeListener(threshold) {

    override fun onStateChanged(layout: AppBarLayout?, state: State?) {
        activity.supportActionBar?.title = if (state == State.COLLAPSED) title else null
    }
}
