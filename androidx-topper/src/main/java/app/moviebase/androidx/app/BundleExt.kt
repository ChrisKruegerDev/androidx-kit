package app.moviebase.androidx.app

import android.os.Bundle

@Deprecated("use bundleOf from KTX directly")
inline fun bundle(block: Bundle.() -> Unit) = Bundle().apply(block)

