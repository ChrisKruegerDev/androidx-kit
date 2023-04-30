package app.moviebase.androidx.view

import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun ViewPager.onPageSelected(callback: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            callback(position)
        }
    })
}

fun ViewPager.updateCurrentItem(position: Int) {
    if (currentItem != position)
        currentItem = position
}


fun TabLayout.setupViewPager2(viewPager: ViewPager2, nameArrayRes: Int) {
    val names = resources.getStringArray(nameArrayRes)
    setupViewPager2(viewPager, names)
}

fun TabLayout.setupViewPager2(viewPager: ViewPager2, names: Array<String>? = null) {
    TabLayoutMediator(this, viewPager) { tab, position ->
        if(names != null) tab.text = names[position]
    }.attach()
}

fun ViewPager2.onPageSelected(callback: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            callback(position)
        }
    })
}
