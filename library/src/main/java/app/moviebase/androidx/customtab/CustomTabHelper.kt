package app.moviebase.androidx.customtab

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.ColorInt

object CustomTabHelper {

    fun openCustomTab(context: Context, uri: Uri, @ColorInt color: Int, handleException: (Throwable) -> Unit) {
        CustomTabActivityHelper.openCustomTab(context, uri, { openUrlInBrowser(context, uri, handleException) }, null, color)
    }

    private fun openUrlInBrowser(context: Context, url: Uri, handleException: (Throwable) -> Unit) {
        try {
            val browserIntent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            browserIntent.data = url
            context.startActivity(browserIntent)
        } catch (e: ActivityNotFoundException) {
            // skip exception
        } catch (e: Throwable) {
            handleException(e)
        }
    }
}
