package app.moviebase.androidx.text

import android.os.Build
import android.text.Html

fun CharSequence.toHtml(): CharSequence {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(toString())
    }
}
