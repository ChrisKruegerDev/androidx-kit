package app.moviebase.androidx.app

import androidx.fragment.app.Fragment
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.moviebase.androidx.content.preferences

typealias FragmentProvider = () -> Fragment

fun Fragment.registerOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    requireContext().preferences.registerOnSharedPreferenceChangeListener(l)
}

fun Fragment.unregisterOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    requireContext().preferences.unregisterOnSharedPreferenceChangeListener(l)
}

val Fragment.isAttached: Boolean get() = activity != null && isAdded
val Fragment.isNotAttached: Boolean get() = !isAttached

inline fun <T : Fragment> T.applyArguments(action: Bundle.() -> Unit): T {
    arguments = Bundle().apply { action(this) }
    return this
}

fun Fragment.requireAppCompatActivity() = requireActivity() as AppCompatActivity
