package app.moviebase.androidx.preference

import androidx.annotation.StringRes
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

fun <T : Preference> PreferenceFragmentCompat.preference(key: CharSequence): T {
    return findPreference(key) ?: throw IllegalStateException("could not find preference with key '$key'")
}

fun <T : Preference> PreferenceFragmentCompat.preference(@StringRes resId: Int): T {
    val key = getString(resId)
    return preference(key)
}
