package com.moviebase.androidx.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.moviebase.androidx.R
import com.moviebase.ktx.content.toPx
import com.moviebase.ktx.view.marginTopValue

object ViewHelper {

    fun setViewMarginStatusBarHeight(v: View, offset: Int? = null) {
        val margin = getStatusBarHeight(v.context)
        val offsetDp = offset?.toPx(v.context) ?: 0
        v.marginTopValue = margin + offsetDp
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getActionBarHeight(activity: Context): Int {
        val typedValue = TypedValue()

        var attributeResourceId = android.R.attr.actionBarSize
        if (activity is AppCompatActivity)
            attributeResourceId = R.attr.actionBarSize

        val resolveAttribute = activity.theme.resolveAttribute(attributeResourceId, typedValue, true)

        return if (resolveAttribute)
            TypedValue.complexToDimensionPixelSize(typedValue.data, activity.resources.displayMetrics)
        else
            Math.floor(activity.resources.getDimensionPixelSize(R.dimen.actionBarHeight).toDouble()).toInt()
    }

}
