package app.moviebase.androidx.app

import android.app.Activity
import android.app.ActivityOptions
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ShareCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import app.moviebase.androidx.content.preferences
import kotlin.reflect.KClass
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.startActivityWithFade(intent: Intent) {
    startActivity(intent, makeFadeAnimation())
}

fun Activity.startActivityWithFade(cls: KClass<*>) = startActivityWithFade(Intent(this, cls.java))

fun Activity.makeFadeAnimation(): Bundle {
    val options = ActivityOptions.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out)
    return options.toBundle()
}

fun Activity.makeSceneTransitionAnimation(view: View): Bundle? {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, view.transitionName)
    return options.toBundle()
}

val Activity.view: View? get() = findViewById(android.R.id.content)

fun Activity.requireView() = requireNotNull(view) {
    "Main view is not available for: $this"
}

fun Activity.shareIntentChooser(uri: String, title: String?): Boolean = try {
    ShareCompat.IntentBuilder.from(this)
        .setText(uri)
        .setSubject(title)
        .setType("text/plain")
        .startChooser()
    true
} catch (e: ActivityNotFoundException) {
    false
}


fun Activity.startShareChooser(uri: String, title: String?) {
    ShareCompat.IntentBuilder.from(this)
        .setText(uri)
        .setSubject(title)
        .setType("text/plain")
        .startChooser()
}

fun Activity.startChooser(intent: Intent, title: String?) = try {
    val chooser = Intent.createChooser(intent, title)
    startActivity(chooser)
    true
} catch (e: Throwable) {
    false
}


fun Activity.registerOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    preferences.registerOnSharedPreferenceChangeListener(l)
}

fun Activity.unregisterOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    preferences.unregisterOnSharedPreferenceChangeListener(l)
}

fun AppCompatActivity.setToolbarIcon(@DrawableRes resId: Int) =
    supportActionBar?.let {
        it.setHomeAsUpIndicator(resId)
        it.setDisplayHomeAsUpEnabled(true)
    }

/**
 * When creating the NavHostFragment using FragmentContainerView or
 * if manually adding the NavHostFragment to your activity via a FragmentTransaction,
 * attempting to retrieve the NavController in onCreate() of an Activity via Navigation.findNavController(Activity, @IdRes int) will fail.
 * You should retrieve the NavController directly from the NavHostFragment instead.
 */
fun AppCompatActivity.findNavControllerByFragment(@IdRes fragmentId: Int): NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(fragmentId) as NavHostFragment
    return navHostFragment.navController
}

@Suppress("DEPRECATION")
fun Activity.hideSystemBars() {
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

@Suppress("DEPRECATION")
fun Activity.showSystemBars() {
    val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

