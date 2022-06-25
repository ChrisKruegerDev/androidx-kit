package app.moviebase.androidx.action

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.moviebase.ktx.app.startActivityWithFade
import kotlin.reflect.KClass

abstract class StartActivityAction(
    private val type: KClass<out Activity>
) : Action {

    override fun execute(activity: FragmentActivity, fragment: Fragment?) {
        try {
            val intent = Intent(activity, type.java)
            applyIntent(intent)
            activity.startActivityWithFade(intent)
        } catch (t: Throwable) {
            whenFailed(t)
        }
    }

    abstract fun applyIntent(intent: Intent)

    abstract fun whenFailed(t: Throwable)
}

