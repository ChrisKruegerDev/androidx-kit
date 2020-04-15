package com.placebox.androidx.view

import android.content.Context
import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class LayoutContainerView(override val containerView: View) : LayoutContainer {

    protected val context: Context
            get() = containerView.context

}
