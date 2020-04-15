package com.placebox.androidx.transition

import android.transition.*
import androidx.core.transition.doOnEnd


fun ImageChangeTransitionSet(onEnd: () -> Unit) =
    TransitionSet().addTransition(ChangeImageTransform())
        .addTransition(ChangeBounds())
        .setDuration(300L)
        .apply {
            doOnEnd { onEnd() }
        }

