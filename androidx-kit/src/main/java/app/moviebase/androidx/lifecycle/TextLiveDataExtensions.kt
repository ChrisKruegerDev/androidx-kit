package app.moviebase.androidx.lifecycle

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import app.moviebase.androidx.view.textOrGone
import app.moviebase.androidx.view.updateText

val LiveData<CharSequence>.stringValue: String?
    get() = value?.toString()

fun <T : CharSequence?> LiveData<T>.bindTextView(owner: LifecycleOwner, textView: TextView) =
    bind(owner) { textView.updateText(it) }

fun <T : CharSequence?> LiveData<T>.bindTextViewOrGone(owner: LifecycleOwner, textView: TextView) =
    bind(owner) { textView.textOrGone = it }
