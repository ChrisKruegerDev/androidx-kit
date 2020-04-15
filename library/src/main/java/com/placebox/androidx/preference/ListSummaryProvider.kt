package com.placebox.androidx.preference

import androidx.preference.ListPreference
import androidx.preference.Preference
import com.placebox.ktx.type.DASH

typealias Formatter = CharSequence.() -> CharSequence

class ListSummaryProvider : Preference.SummaryProvider<ListPreference> {

    var format: Formatter? = null

    override fun provideSummary(preference: ListPreference?): CharSequence {
        val entry = preference?.entry
        val format = format

        return when {
            entry.isNullOrBlank() -> return DASH
            format != null        -> entry.format()
            else                  -> entry
        }
    }

}
