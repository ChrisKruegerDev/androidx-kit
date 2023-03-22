package app.moviebase.androidx.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import app.moviebase.androidx.content.toPx

class RectViewOutlineProvider(private val radius: Float) : ViewOutlineProvider() {

    constructor(resources: Resources, dp: Int) : this(dp.toPx(resources).toFloat())
    constructor(context: Context, dp: Int) : this(context.resources, dp)
    constructor(view: View, dp: Int) : this(view.resources, dp)

    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(0, 0, view.width, view.height, radius)
    }

}
