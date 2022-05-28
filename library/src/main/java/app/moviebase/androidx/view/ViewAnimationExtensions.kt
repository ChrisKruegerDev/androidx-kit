package app.moviebase.androidx.view

import android.content.Context
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.TransitionRes
import app.moviebase.androidx.R
import app.moviebase.androidx.widget.animation.BounceInterpolator

fun View.animateBounce() {
    val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
    val interpolator = BounceInterpolator(0.2, 20.0)
    anim.interpolator = interpolator
    startAnimation(anim)
}

fun View.expandIconWithText(
    expanded: Boolean,
    icon: View,
    text: View? = null,
    @TransitionRes resource: Int
) {
    val toggle = context.translation(resource)
    toggle.duration = if (expanded) 300L else 200L
    TransitionManager.beginDelayedTransition(parent as ViewGroup, toggle)

    icon.rotation = if (expanded) 180f else 0f
    icon.isActivated = expanded
    text?.isActivated = expanded
}

private fun Context.translation(@TransitionRes resource: Int) =
    TransitionInflater.from(this).inflateTransition(resource)
