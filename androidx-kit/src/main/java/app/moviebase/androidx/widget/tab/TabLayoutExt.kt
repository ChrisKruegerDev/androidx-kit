package app.moviebase.androidx.widget.tab

import android.view.ViewGroup
import androidx.annotation.ArrayRes
import androidx.annotation.DimenRes
import androidx.core.view.postDelayed
import com.google.android.material.tabs.TabLayout

fun TabLayout.addNewTab(title: String, tag: Any? = null): TabLayout.Tab {
    val tab = newTab().setText(title).setTag(tag)
    addTab(tab)
    return tab
}

fun TabLayout.addTabs(@ArrayRes arrayTitles: Int) {
    resources.getStringArray(arrayTitles).forEach {
        addNewTab(it)
    }
}

fun TabLayout.addTabs(tabs: Iterable<String>) = tabs.forEach { addNewTab(it) }

fun TabLayout.selectByPosition(position: Int) = getTabAt(position)?.select()

fun TabLayout.selectNext() {
    val position = selectedTabPosition + 1
    selectByPosition(position)
}

fun TabLayout.selectPrevious() {
    val position = selectedTabPosition -1
    selectByPosition(position)
}

fun TabLayout.selectTabByTag(tagId: Any?): Boolean {
    if (tagId == null) return false

    for (i in 0 until tabCount) {
        val tab = getTabAt(i)
        if (tab != null && tab.tag == tagId) {
            if(!tab.isSelected) {
                postDelayed(100) {
                    if(tab.parent != null) {
                        tab.select()
                    }
                }
            }
            return true
        }
    }

    return false
}

fun TabLayout.onTabSelected(callback: (TabLayout.Tab?) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            callback(tab)
        }
    })
}

fun TabLayout.addMargin(@DimenRes id: Int, childIndex: Int = 0) {
    val tab = (getChildAt(0) as ViewGroup).getChildAt(childIndex)
    val params = tab.layoutParams as ViewGroup.MarginLayoutParams
    val marginEnd = resources.getDimensionPixelSize(id)
    params.setMargins(0, 0, marginEnd, 0)
    tab.requestLayout()
}


fun TabLayout.addMargins(@DimenRes id: Int) {
    if(id == 0) return

    val viewGroup = getChildAt(0) as ViewGroup
    val margin = resources.getDimensionPixelSize(id)

    (0..(tabCount - 2)).forEach {
        val tab = viewGroup.getChildAt(it)
        val params = tab?.layoutParams as ViewGroup.MarginLayoutParams?
        params?.setMargins(0, 0, margin, 0)
        tab.requestLayout()
    }
}
