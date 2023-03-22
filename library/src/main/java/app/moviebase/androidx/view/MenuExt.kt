package app.moviebase.androidx.view

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.VectorDrawable
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ColorInt
import androidx.core.graphics.alpha
import androidx.core.text.toSpannable
import app.moviebase.androidx.text.withColor
import app.moviebase.androidx.type.alpha

fun Menu.colorItemTitle(id: Int, @ColorInt color: Int) {
    val item = findItem(id) ?: return
    item.title = item.title?.toSpannable()?.withColor(color)
}

fun Menu.tint(@ColorInt color: Int) {
    for (i in 0 until this.size()) {
        getItem(i).tint(color)
    }
}

fun MenuItem.tint(@ColorInt color: Int) {
    when(val icon = icon) {
        is GradientDrawable -> icon.setColor(color)
        is VectorDrawable -> icon.setTint(color)
    }
}

fun MenuItem.updateEnabled(enabled: Boolean) {
    isEnabled = enabled
    icon?.alpha = if (enabled) 1.alpha else 0.5.alpha
}

fun Menu.setEnabled(enabled: Boolean) {
    for (i in 0 until size()) {
        getItem(i).updateEnabled(enabled)
    }
}
