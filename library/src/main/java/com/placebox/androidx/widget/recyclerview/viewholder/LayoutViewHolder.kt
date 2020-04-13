package com.placebox.androidx.widget.recyclerview.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class LayoutViewHolder constructor(
    final override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val context: Context get() = containerView.context

    constructor(
        parent: ViewGroup,
        @LayoutRes resource: Int
    ) : this(LayoutInflater.from(parent.context).inflate(resource, parent, false))


}
