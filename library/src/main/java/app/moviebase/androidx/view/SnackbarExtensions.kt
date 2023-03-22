package app.moviebase.androidx.view

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import app.moviebase.androidx.lifecycle.SingleLiveEvent
import app.moviebase.androidx.lifecycle.bind
import app.moviebase.androidx.type.isNullOrFalse
import com.google.android.material.snackbar.Snackbar

typealias ViewProvider = () -> View?

data class SnackbarMessage(
    val text: CharSequence,
    val duration: Int = Snackbar.LENGTH_LONG,
    val action: SnackbarAction? = null,
    @ColorInt val textColor: Int? = null,
    @ColorInt val backgroundColor: Int? = null
)

data class SnackbarAction(
    val text: CharSequence,
    val dispatch: () -> Unit
)

@Suppress("unused")
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
    anchorViewProvider?.let {
        val anchorView = it()
        snackbar.anchorView = if (anchorView?.isVisible.isNullOrFalse()) {
            null
        } else {
            anchorView
        }
    }

    message.action?.let { action -> snackbar.setAction(action.text) { action.dispatch() } }
    message.backgroundColor?.let { snackbar.setBackgroundTint(it) }
    message.textColor?.let { snackbar.setTextColor(it) }

    return snackbar.show()
}

@Suppress("unused")
fun SingleLiveEvent<SnackbarMessage>.bindTo(lifecycleOwner: LifecycleOwner, view: View, anchorViewProvider: ViewProvider? = null) {
    bind(lifecycleOwner) {
        it?.let { view.showSnackbar(message = it, anchorViewProvider = anchorViewProvider) }
    }
}

