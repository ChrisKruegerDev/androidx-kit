package com.placebox.androidx.action

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.placebox.androidx.lifecycle.SingleLiveEvent
import com.placebox.ktx.app.showDialogFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface Action {
    fun execute(activity: FragmentActivity, fragment: Fragment?)
}

class ActionObserver(
    private val activity: FragmentActivity,
    private val fragment: Fragment? = null
) : Observer<Action> {

    override fun onChanged(action: Action?) {
        action?.execute(activity, fragment)
    }

}

fun SingleLiveEvent<Action>.bindTo(fragment: Fragment) =
    observe(fragment.viewLifecycleOwner, ActionObserver(fragment.requireActivity(), fragment))

fun SingleLiveEvent<Action>.bindTo(activity: AppCompatActivity) =
    observe(activity, ActionObserver(activity))


abstract class ShowDialogFragmentAction(private val cls: KClass<out DialogFragment>) : Action {

    open val args: Bundle? = null

    final override fun execute(activity: FragmentActivity, fragment: Fragment?) {
        val fragmentManager = fragment?.childFragmentManager ?: activity.supportFragmentManager
        fragmentManager.showDialogFragment(cls.java.simpleName, cls::createInstance, args)
    }

}
