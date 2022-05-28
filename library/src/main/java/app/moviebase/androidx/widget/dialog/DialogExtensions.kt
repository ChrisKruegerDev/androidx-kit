package app.moviebase.androidx.widget.dialog

import android.app.Dialog
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Dialog.addStateExpanded() {
    setOnShowListener { dialog ->
        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        checkNotNull(bottomSheet)

        val behaviour = BottomSheetBehavior.from(bottomSheet)
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
