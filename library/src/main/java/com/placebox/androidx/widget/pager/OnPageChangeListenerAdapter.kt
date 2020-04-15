package com.placebox.androidx.widget.pager

import androidx.viewpager.widget.ViewPager

abstract class OnPageChangeListenerAdapter: ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

}
