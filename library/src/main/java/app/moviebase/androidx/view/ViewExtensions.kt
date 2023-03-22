package app.moviebase.androidx.view

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
import app.moviebase.androidx.content.hideKeyboard
import app.moviebase.androidx.content.showKeyboard
import kotlin.reflect.KClass

inline fun <reified T : View> View.viewId(@IdRes id: Int): Lazy<T> = lazy { findViewById<T>(id) }

inline fun <reified T : View> View.findParent() = findParentOf(T::class)

@Suppress("UNCHECKED_CAST")
tailrec fun <T : View> View.findParentOf(clazz: KClass<T>): T? {
    val viewParent = parent
    if (viewParent == null || viewParent !is View) return null
    if (clazz.isInstance(viewParent)) return viewParent as T
    return viewParent.findParentOf(clazz)
}

fun View.hideKeyboard() = context.hideKeyboard(windowToken)

fun View.showKeyboard() = context.showKeyboard(this)

fun View.updateEnabled(enable: Boolean, alphaValue: Double = .4) {
    isEnabled = enable

    if (enable) {
        alpha = 1f
        visibility = View.VISIBLE
    } else {
        alpha = alphaValue.toFloat()
    }
}

var View.marginTopValue
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: (layoutParams as? ConstraintLayout.LayoutParams)?.topMargin ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
            it.setMargins(it.leftMargin, value, it.rightMargin, it.bottomMargin)
        }
        (layoutParams as? ConstraintLayout.LayoutParams)?.let {
            it.topMargin = value
            this.layoutParams = it
        }
    }

/**
 * Only update margin if it has changed. This avoid moving the FAB.
 */
fun View.updateBottomMargin(value: Int) {
    if (marginBottom != value)
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = value
        }
}

fun View.updateTopMargin(value: Int) {
    if (marginTop != value)
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = value
        }
}


fun View.updateSideMargin(value: Int) {
    if (marginRight != value && marginLeft != value)
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            rightMargin = value
            leftMargin = value
        }
}

fun View.updateHeight(value: Int) = updateLayoutParams {
    height = value
}

fun View.applyTopWindowInsets() {
    doOnApplyWindowInsets { view, insets, padding ->
        view.updatePadding(top = padding.top + insets.systemWindowInsets.top)
    }
}

fun View.applyBottomWindowInsets() {
    doOnApplyWindowInsets { view, insets, padding ->
        view.updatePadding(bottom = padding.bottom + insets.systemWindowInsets.bottom)
    }
}

fun View.doOnApplyWindowInsets(onApply: (view: View, insets: WindowInsetsCompat, padding: ViewPaddingState) -> Unit) {
    val paddingState = createStateForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        onApply(v, insets, paddingState)
        insets
    }
    requestApplyInsetsWhenAttached()
}

private fun createStateForView(view: View) = ViewPaddingState(
    view.paddingLeft,
    view.paddingTop,
    view.paddingRight,
    view.paddingBottom,
    view.paddingStart,
    view.paddingEnd
)

data class ViewPaddingState(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val start: Int,
    val end: Int
)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.requestApplyInsets()
            }
            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
