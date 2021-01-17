package com.moviebase.androidx.view

import android.view.View
import kotlin.reflect.KClass


class ViewLookup() {

    fun <T : View> findParentOfView(view: View, clazz: KClass<T>): T? = view.findParent(clazz)

    @Suppress("UNCHECKED_CAST")
    private tailrec fun <T : View> View.findParent(clazz: KClass<T>): T? {
        val viewParent = parent

        if (viewParent == null || viewParent !is View) return null
        if (clazz.isInstance(viewParent)) return viewParent as T

        return viewParent.findParent(clazz)
    }

}
