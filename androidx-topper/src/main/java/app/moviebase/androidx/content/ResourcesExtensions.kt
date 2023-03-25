package app.moviebase.androidx.content

import android.content.Context
import android.content.res.Resources

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.toPx() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
fun Int.toPx() = px
fun Int.toDp() = dp

@Deprecated("Use without the input parameter", replaceWith = ReplaceWith("toPx"))
fun Float.toPx(resources: Resources) = (this * resources.displayMetrics.density + 0.5f).toInt()
@Deprecated("Use without the input parameter", replaceWith = ReplaceWith("toPx"))
fun Float.toPx(context: Context) = toPx(context.resources)
@Deprecated("Use without the input parameter", replaceWith = ReplaceWith("toPx"))
fun Int.toPx(context: Context) = toFloat().toPx(context.resources)
@Deprecated("Use without the input parameter", replaceWith = ReplaceWith("toPx"))
fun Int.toPx(resources: Resources) = toFloat().toPx(resources)
