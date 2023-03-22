package app.moviebase.androidx.net

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

fun Uri.tryOpen(context: Context, block: ((Throwable) -> Unit)? = null) {
    try {
        open(context)
    } catch (t: Throwable) {
        block?.invoke(t)
    }
}

fun String.tryToUri(): Uri? = try {
    toUri()
} catch (t: Throwable) {
    null
}

fun Uri.open(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, this)
    context.startActivity(intent)
}
