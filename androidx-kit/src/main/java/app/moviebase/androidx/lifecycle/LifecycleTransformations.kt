package app.moviebase.androidx.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map

object LifecycleTransformations {

    fun <X, Y> map(source: LiveData<X>, mapFunction: (X) -> Y) =
        source.map(mapFunction) as MediatorLiveData<Y>
}
