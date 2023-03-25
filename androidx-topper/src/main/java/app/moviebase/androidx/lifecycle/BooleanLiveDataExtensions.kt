package app.moviebase.androidx.lifecycle

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.moviebase.androidx.type.isNullOrFalse
import app.moviebase.androidx.type.isTrue

val LiveData<Boolean>.booleanValue: Boolean
    get() = value.isTrue()

fun MutableLiveData<Boolean>.switch() {
    value = !value.isTrue()
}

fun MutableLiveData<Boolean>.bindEnable(owner: LifecycleOwner, view: View) {
    observe(owner) { view.isEnabled = it.isTrue() }
}

fun LiveData<Boolean>.bindVisible(owner: LifecycleOwner, view: View) =
    bind(owner) { view.isVisible = it.isTrue() }

fun LiveData<Boolean>.bindVisible(owner: LifecycleOwner, vararg views: View) =
    bind(owner) { views.forEach { view -> view.isVisible = it.isTrue() } }

fun LiveData<Boolean>.bindVisibleOrInvisible(owner: LifecycleOwner, view: View) =
    bind(owner) { view.isInvisible = it.isNullOrFalse() }

fun LiveData<Boolean>.bindOnTrue(owner: LifecycleOwner, onChange: () -> Unit) =
    bind(owner) { if (it.isTrue()) onChange() }
