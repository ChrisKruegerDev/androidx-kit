package app.moviebase.androidx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

val LiveData<Int?>.intValue get() = value ?: 0

fun LiveData<Int?>.bindWithZero(owner: LifecycleOwner, onChange: (Int) -> Unit) =
    bind(owner) { onChange(it ?: 0) }
