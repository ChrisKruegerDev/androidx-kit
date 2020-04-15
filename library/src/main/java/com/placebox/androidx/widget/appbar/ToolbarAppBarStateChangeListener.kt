package com.placebox.androidx.widget.appbar

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout

class ToolbarAppBarStateChangeListener(
    threshold: Int,
    private val activity: AppCompatActivity,
    private val upIndicationResId: Int,
    private val upIndicationResIdCollapsed: Int,
    private val titleCollapsed: () -> CharSequence?,
    private val subtitleCollapsed: (() -> CharSequence?)? = null
) : AppBarStateChangeListener(threshold * -1) {

    override fun onStateChanged(layout: AppBarLayout?, state: State?) {
        if (state == State.COLLAPSED) {
            activity.supportActionBar?.apply {
                title = titleCollapsed()
                subtitle = subtitleCollapsed?.invoke()
                setHomeAsUpIndicator(upIndicationResIdCollapsed)
            }
        } else {
            activity.supportActionBar?.apply {
                title = null
                subtitle = null
                setHomeAsUpIndicator(upIndicationResId)
            }
        }
    }
}
