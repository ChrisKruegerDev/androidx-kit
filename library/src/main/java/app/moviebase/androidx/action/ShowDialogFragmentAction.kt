package app.moviebase.androidx.action

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.moviebase.ktx.app.showDialogFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class ShowDialogFragmentAction(private val cls: KClass<out DialogFragment>) : Action {

    open val args: Bundle? = null

    final override fun execute(activity: FragmentActivity, fragment: Fragment?) {
        val fragmentManager = fragment?.childFragmentManager ?: activity.supportFragmentManager
        fragmentManager.showDialogFragment(cls.java.simpleName, { cls.createInstance() }, args)
    }
}

