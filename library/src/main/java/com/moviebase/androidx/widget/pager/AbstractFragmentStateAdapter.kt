package com.moviebase.androidx.widget.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moviebase.androidx.app.FragmentProvider


abstract class AbstractFragmentStateAdapter : FragmentStateAdapter {

    constructor(fragment: Fragment) : super(fragment)
    constructor(activity: FragmentActivity) : super(activity)

    abstract val fragments: Array<FragmentProvider>

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        if (position < 0 || position >= fragments.size)
            throw IllegalArgumentException("invalid position: $position")

        return fragments[position]()
    }

}
