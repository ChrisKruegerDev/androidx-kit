package app.moviebase.androidx.view

import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible

fun Group.applyReferences(apply: View.() -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).apply()
    }
}

fun Group.applyVisible(isVisible: Boolean) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).isVisible = isVisible
    }
}
