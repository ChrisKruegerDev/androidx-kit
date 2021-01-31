package com.moviebase.androidx.widget.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.moviebase.androidx.app.FragmentProvider


abstract class TitlePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    abstract val titles: Array<String>
    abstract val fragments: Array<FragmentProvider>

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

    override fun getItem(position: Int) = createFragment(position)

    private fun createFragment(pos: Int): Fragment {
        if (titles.size != fragments.size)
            throw IllegalStateException("invalid values: ${titles.size} titles, ${fragments.size} fragments")

        if (pos < 0 || pos >= fragments.size)
            throw IllegalArgumentException("invalid position: $pos")

        return fragments[pos]()
    }

}
