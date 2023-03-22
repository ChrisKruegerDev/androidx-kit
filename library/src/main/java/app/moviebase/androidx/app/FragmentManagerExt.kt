package app.moviebase.androidx.app

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.initializeFragment(@IdRes containerId: Int, provider: () -> Fragment) {
    var fragment = findFragmentById(containerId)
    if (fragment == null) {
        fragment = provider()
        replaceFragment(containerId, fragment)
    }
}

fun FragmentManager.replaceFragment(@IdRes containerId: Int, fragment: Fragment, tag: String? = null) {
    execute { replace(containerId, fragment, tag) }
}

inline fun FragmentManager.execute(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commit()
}

inline fun FragmentManager.showDialogFragment(tag: String, provider: () -> DialogFragment, args: Bundle? = null) {
    val fragment = findFragmentByTag(tag) as DialogFragment? ?: provider()
    fragment.arguments = args

    if (!fragment.isAdded)
        fragment.show(this, tag)
}
