package com.placebox.androidx.widget.navigation

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

class VisibleBottomSheetCallback(
        private val bottomSheetBehavior: BottomSheetBehavior<*>
) : BottomSheetBehavior.BottomSheetCallback() {

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        if (newState == BottomSheetBehavior.STATE_HIDDEN)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}
