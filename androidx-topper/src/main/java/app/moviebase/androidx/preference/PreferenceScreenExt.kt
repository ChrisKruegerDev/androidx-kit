package app.moviebase.androidx.preference

import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceScreen
import androidx.preference.SwitchPreference

fun PreferenceScreen.addPreference(block: Preference.() -> Unit): Preference {
    val preference = Preference(context)
    preference.block()
    addPreference(preference)
    return preference
}

fun PreferenceScreen.addListPreference(block: ListPreference.() -> Unit): ListPreference {
    val preference = ListPreference(context)
    preference.block()
    addPreference(preference)
    return preference
}

fun PreferenceScreen.addSwitchPreference(block: SwitchPreference.() -> Unit): SwitchPreference {
    val preference = SwitchPreference(context)
    preference.block()
    addPreference(preference)
    return preference
}
