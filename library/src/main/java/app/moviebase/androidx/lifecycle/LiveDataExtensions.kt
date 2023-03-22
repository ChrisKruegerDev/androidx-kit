package app.moviebase.androidx.lifecycle

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>>?.isNullOrEmpty(): Boolean = this?.value.isNullOrEmpty()
fun <T> MutableLiveData<List<T>>?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()
fun <T> LiveData<List<T>?>.bindWithEmptyList(owner: LifecycleOwner, onChange: (List<T>) -> Unit) =
    bind(owner) { onChange(it ?: emptyList()) }

fun <T> LiveData<T?>.requireValue(): T = value ?: throw NullPointerException("value is not available")

fun <T> MutableLiveData<T>.trigger() {
    value = value
}

fun <T> MutableLiveData<T>.updateValue(newValue: T) {
    if (value != newValue) value = newValue
}

fun <T> LiveData<T>.bind(owner: LifecycleOwner, onChange: (T) -> Unit) {
    val lifecycleOwner = if (owner is Fragment) owner.viewLifecycleOwner else owner
    observe(lifecycleOwner, { onChange(it) })
}

fun <T> LiveData<T>.bindNotNull(owner: LifecycleOwner, onChange: (T) -> Unit) =
    bind(owner) { if (it != null) onChange(it) }

fun <T : Any?> LiveData<T>.bindNullableAsSelected(owner: LifecycleOwner, view: View) =
    bind(owner) { view.isSelected = it != null }

fun <T : Any?> LiveData<T>.bindNullableAsVisible(owner: LifecycleOwner, view: View) =
    bind(owner) { view.isVisible = it != null }
