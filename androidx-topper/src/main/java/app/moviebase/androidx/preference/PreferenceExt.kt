package app.moviebase.androidx.preference

import androidx.preference.Preference

fun Preference.onPreferenceChange(block: (Any) -> Unit) {
    setOnPreferenceChangeListener { _, newValue ->
        block(newValue)
        true
    }
}
