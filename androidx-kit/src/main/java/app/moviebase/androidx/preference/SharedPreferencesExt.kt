package app.moviebase.androidx.preference

import android.content.SharedPreferences
import androidx.core.content.edit

fun preferenceChangeListener(block: (String?) -> Unit) =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key -> block(key) }

fun SharedPreferences.set(key: String, value: String?) = edit { putString(key, value) }
fun SharedPreferences.set(key: String, value: Set<String>) = edit { putStringSet(key, value) }
fun SharedPreferences.set(key: String, value: Int) = edit { putInt(key, value) }
fun SharedPreferences.set(key: String, value: Long) = edit { putLong(key, value) }
fun SharedPreferences.set(key: String, value: Float) = edit { putFloat(key, value) }
fun SharedPreferences.set(key: String, value: Boolean) = edit { putBoolean(key, value) }

fun SharedPreferences.setNullableBoolean(key: String, value: Boolean?) = edit {
    if (value == null) removeValue(key) else putBoolean(key, value)
}

fun SharedPreferences.getNullableBoolean(key: String): Boolean? = if (contains(key)) getBoolean(key, false) else null

fun SharedPreferences.getNotNullString(key: String, defaultValue: String): String = getString(key, defaultValue) ?: defaultValue

fun SharedPreferences.removeValue(key: String) = edit { remove(key) }

fun SharedPreferences.clear() = edit { clear() }
