package app.moviebase.androidx.text

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.*
import androidx.annotation.ColorInt


fun CharSequence.concatNewLine() = concatWith("\n")
fun CharSequence.appendWhitespace() = concatWith(" ")

fun String.formatBold(text: String) = setPlaceholderSpannable(StyleSpan(Typeface.BOLD), text)
fun String.formatSize(size: Int, text: String) = setPlaceholderSpannable(AbsoluteSizeSpan(size, false), text)

fun String.setPlaceholderSpannable(what: Any, text: String): SpannableString {
    val index = indexOf("%s")
    val spannable = SpannableString(this.format(text))
    if (index != -1)
        spannable.setSpan(what, index, index + text.length, 0)

    return spannable
}

fun String.setPlaceholderSpannables(text: String, vararg what: Any): SpannableString {
    val index = indexOf("%s")
    val spannable = SpannableString(this.format(text))
    if (index != -1)
        what.forEach { spannable.setSpan(it, index, index + text.length, 0) }

    return spannable
}


fun CharSequence.concatWith(other: CharSequence): CharSequence = run {
    return TextUtils.concat(this, other)
}

fun CharSequence.wrapWhitespaces() = StringBuilder(length + 2)
        .append(" ")
        .append(this@wrapWhitespaces)
        .append(" ")
        .toString()

fun Spannable.withAbsoluteSize(size: Int, dp: Boolean = false) = setExclusiveSpan(AbsoluteSizeSpan(size, dp))
fun Spannable.withRelativeSize(proportion: Float) = setExclusiveSpan(RelativeSizeSpan(proportion))
fun Spannable.withBold() = withStyle(Typeface.BOLD)
fun Spannable.withStyle(style: Int) = setExclusiveSpan(StyleSpan(style))
fun Spannable.withColor(@ColorInt color: Int) = setExclusiveSpan(ForegroundColorSpan(color))
fun Spannable.withBackgroundColor(@ColorInt color: Int) = setExclusiveSpan(BackgroundColorSpan(color))
fun Spannable.withStrikethrough() = apply {
    setSpan(StrikethroughSpan(), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

private fun Spannable.setExclusiveSpan(what: Any) = apply {
    setSpan(what, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableStringBuilder.withBold() = withStyle(Typeface.BOLD)
fun SpannableStringBuilder.withStyle(style: Int) = setExclusiveSpan(StyleSpan(style))

private fun SpannableStringBuilder.setExclusiveSpan(what: Any) = apply {
    setSpan(what, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}
