package app.moviebase.androidx.app

import android.app.Activity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.hideSystemBars() {
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

fun Activity.showSystemBars() {
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}
