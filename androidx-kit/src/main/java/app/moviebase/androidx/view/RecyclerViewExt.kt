package app.moviebase.androidx.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.moviebase.androidx.content.toPx

fun RecyclerView.setPaddingSidesPx(px: Int) {
    setPadding(px, paddingTop, px, paddingBottom)
}

fun RecyclerView.setPaddingTop(dp: Int) {
    val px = if (dp == 0) 0 else dp.toPx(context)
    setPaddingTopPx(px)
}

fun RecyclerView.setPaddingBottom(dp: Int) {
    val px = if (dp == 0) 0 else dp.toPx(context)
    setPadding(paddingStart, paddingTop, paddingEnd, px)
}

fun RecyclerView.setPaddingBottomPx(px: Int) {
    setPadding(paddingStart, paddingTop, paddingEnd, px)
}

fun RecyclerView.setPaddingTopPx(px: Int) {
    setPadding(paddingStart, px, paddingEnd, paddingBottom)
}

var RecyclerView.pool: RecyclerView.RecycledViewPool
    get() = recycledViewPool
    set(value) {
        setRecycledViewPool(value)
        (layoutManager as? LinearLayoutManager)?.recycleChildrenOnDetach = true
    }

fun RecyclerView.Adapter<*>.unregisterObserverIfAvailable(observer: RecyclerView.AdapterDataObserver) {
    if (hasObservers()) unregisterAdapterDataObserver(observer)
}

