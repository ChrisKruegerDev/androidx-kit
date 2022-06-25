package app.moviebase.androidx.app

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Move into KTX
val Context.isLandscape
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun Dialog.addStateExpandedIfLandscape() {
    if (!context.isLandscape) return
    addStateExpanded()
}

fun Dialog.addStateExpanded() {
    setOnShowListener { dialog ->
        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        checkNotNull(bottomSheet)

        val behaviour = BottomSheetBehavior.from(bottomSheet)
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
