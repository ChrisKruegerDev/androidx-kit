package com.placebox.androidx.widget.appbar

import android.view.View
import com.placebox.ktx.content.toPx

class TranslationAppBarStateChangeListener(private val view: View) : PercentAppBarStateChangeListener() {

    private val translation = 24.toPx(view.context)

    override fun onChanged(percentage: Int) {
        val scale = 1f - percentage.toFloat() / 100
        view.alpha = scale
        view.scaleX = scale
        view.scaleY = scale
        view.translationX = translation - (translation * scale)
    }

}

