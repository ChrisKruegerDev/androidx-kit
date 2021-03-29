package app.moviebase.androidx.view

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import app.moviebase.androidx.lifecycle.SingleLiveEvent
import app.moviebase.ktx.lifecycle.bind
import app.moviebase.ktx.type.isNullOrFalse

typealias ViewProvider = () -> View?

data class SnackbarMessage(
    val text: CharSequence,
    val duration: Int = Snackbar.LENGTH_LONG,
    val action: SnackbarAction? = null,
    @ColorInt val textColor: Int? = null,
    @ColorInt val backgroundColor: Int? = null
)

data class SnackbarAction(
    val textRes: Int? = null,
    val text: CharSequence? = null,
    val dispatch: () -> Unit
)

fun View.showSnackbar(@StringRes textRes: Int) {
    val text = context.getString(textRes)
    showSnackbar(SnackbarMessage(text))
}

/**
 * The view to find a parent from. This view is also used to find the anchor view when
 *     calling {@link Snackbar#setAnchorView(int)}.
 */
fun View.showSnackbar(message: SnackbarMessage, anchorViewProvider: ViewProvider? = null) {
    val snackbar = Snackbar.make(this, message.text, message.duration)
    if (anchorViewProvider != null) {
        try {
            snackbar.anchorView = anchorViewProvider()
            if (snackbar.anchorView?.isVisible.isNullOrFalse())
                snackbar.anchorView = null
        } catch (e: IllegalArgumentException) {
            // hacky solution when anchor view is not available
        }
    }

    val action = message.action
    if (action != null) {
        if (action.textRes != null)
            snackbar.setAction(action.textRes) { action.dispatch() }
        else
            snackbar.setAction(action.text) { action.dispatch() }
    }

    val view = snackbar.view
    if (message.backgroundColor != null)
        (view.background as GradientDrawable).setColor(message.backgroundColor)


    if (message.textColor != null) {
        val textView = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(message.textColor)
    }

    return snackbar.show()
}

fun SingleLiveEvent<SnackbarMessage>.bindTo(lifecycleOwner: LifecycleOwner, view: View, anchorViewProvider: ViewProvider? = null) {
    bind(lifecycleOwner) {
        it?.let { view.showSnackbar(message = it, anchorViewProvider = anchorViewProvider) }
    }
}

