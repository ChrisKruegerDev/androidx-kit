package com.moviebase.androidx.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import com.moviebase.ktx.content.toPx

fun ImageOutlineProvider(resources: Resources): ViewOutlineProvider {
    val radius = 8.toPx(resources).toFloat()
    return ScalingLayoutOutlineProvider(radius)
}

fun ImageOutlineProvider(view: View): ViewOutlineProvider = ImageOutlineProvider(view.resources)
fun ImageOutlineProvider(context: Context): ViewOutlineProvider = ImageOutlineProvider(context.resources)

class ScalingLayoutOutlineProvider(private val radius: Float) : ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(0, 0, view.width, view.height, radius)
    }
}
