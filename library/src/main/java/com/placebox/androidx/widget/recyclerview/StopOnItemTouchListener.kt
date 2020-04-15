package com.placebox.androidx.widget.recyclerview

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class StopOnItemTouchListener : RecyclerView.SimpleOnItemTouchListener() {

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        if (e.action == MotionEvent.ACTION_DOWN && rv.scrollState == RecyclerView.SCROLL_STATE_SETTLING)
            rv.stopScroll()
        return super.onInterceptTouchEvent(rv, e)
    }

}
