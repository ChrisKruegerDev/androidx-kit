package app.moviebase.androidx.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

object LifecycleTransformations {

    fun <X, Y> map(source: LiveData<X>, mapFunction: (X) -> Y) =
        Transformations.map(source, mapFunction) as MediatorLiveData<Y>

}
