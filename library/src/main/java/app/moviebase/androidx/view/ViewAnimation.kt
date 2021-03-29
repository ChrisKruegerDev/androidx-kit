package app.moviebase.androidx.view

import android.view.View
import android.view.animation.AnimationUtils
import app.moviebase.androidx.R
import app.moviebase.androidx.widget.anim.BounceInterpolator

fun View.animateBounce() {
    val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
    val interpolator = BounceInterpolator(0.2, 20.0)
    anim.interpolator = interpolator
    startAnimation(anim)
}
