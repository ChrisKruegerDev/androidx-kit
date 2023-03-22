package app.moviebase.androidx.event

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.moviebase.androidx.lifecycle.SingleLiveEvent
import app.moviebase.androidx.lifecycle.bind

/**
 * Maker interface to send events to the ViewModel.
 */
interface ViewModelEvent

fun interface Dispatcher {
    fun dispatch(event: ViewModelEvent)
}

fun SingleLiveEvent<ViewModelEvent>.bindTo(owner: AppCompatActivity, onChange: (ViewModelEvent?) -> Unit) = bind(owner, onChange)

fun SingleLiveEvent<ViewModelEvent>.bindTo(owner: Fragment, onChange: (ViewModelEvent?) -> Unit) = bind(owner, onChange)
