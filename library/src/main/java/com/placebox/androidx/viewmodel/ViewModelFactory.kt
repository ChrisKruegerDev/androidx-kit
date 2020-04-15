package com.placebox.androidx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class ViewModelFactory(
    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]

        if (creator == null) {
            for (entry in creators.entries) {
                val key = entry.key
                if (modelClass.isAssignableFrom(key)) {
                    creator = entry.value
                    break
                }
            }
        }

        requireNotNull(creator) { "unknown model class $modelClass" }
        return creator.get() as T
    }

}
