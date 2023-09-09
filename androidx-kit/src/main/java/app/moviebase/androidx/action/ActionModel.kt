package app.moviebase.androidx.action

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import app.moviebase.androidx.lifecycle.SingleLiveEvent

interface Action {
    fun execute(activity: FragmentActivity, fragment: Fragment?)
}

class ActionObserver(
    private val activity: FragmentActivity,
    private val fragment: Fragment? = null
) : Observer<Action> {

    override fun onChanged(action: Action) {
        action.execute(activity, fragment)
    }

}

fun SingleLiveEvent<Action>.bindTo(fragment: Fragment) =
    observe(fragment.viewLifecycleOwner, ActionObserver(fragment.requireActivity(), fragment))

fun SingleLiveEvent<Action>.bindTo(activity: AppCompatActivity) =
    observe(activity, ActionObserver(activity))
