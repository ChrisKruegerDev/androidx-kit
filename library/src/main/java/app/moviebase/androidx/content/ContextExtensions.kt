package app.moviebase.androidx.content

import android.app.*
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Color
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import java.util.*

val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
val Context.notificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
val Context.jobScheduler get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
val Context.downloadManager get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
val Context.clipboardManager get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
val Context.searchManager get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager
val Context.inputManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
val Context.connectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
val Context.activityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
val Context.locationManager get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

val Context.appWidgetManager: AppWidgetManager get() = AppWidgetManager.getInstance(this)
val Context.preferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)

val Context.isOnline: Boolean
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    get() {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

val Context.isOffline: Boolean
    get() = !isOnline

val Context.isNightMode: Boolean
    get() = resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES

val Context.isLightMode: Boolean
    get() = resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_NO

@Suppress("DEPRECATION")
val Context.locale: Locale
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        resources.configuration.locales.get(0)
    else
        resources.configuration.locale

val Context.isLandscape
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Context.isTablet
    get() = (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE

fun Context.dimensionPixelSize(@DimenRes id: Int) = resources.getDimensionPixelSize(id)
fun Context.dimension(@DimenRes id: Int) = resources.getDimension(id)
fun Context.integer(@IntegerRes id: Int) = resources.getInteger(id)
fun Context.drawable(id: Int) = ResourcesCompat.getDrawable(resources, id, theme)
fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

@ColorInt
fun Context.colorAttr(attr: Int): Int {
    if (attr == 0) throw NoSuchElementException("attr == 0")
    val typedArray = theme.obtainStyledAttributes(intArrayOf(attr))
    val color = typedArray.getColor(0, Color.DKGRAY)
    typedArray.recycle()
    return color
}

fun Context.dimensionAttr(@AttrRes attr: Int): Float {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    return try {
        a.getDimension(0, 0f)
    } finally {
        a.recycle()
    }
}


val Context.isLowMemory: Boolean
    get() {
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        return memoryInfo.lowMemory
    }

val Context.firstInstallTime: Long?
    get() = try {
        packageManager.getPackageInfo(packageName, 0).firstInstallTime
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

val Context.lastUpdateTime: Long?
    get() = try {
        packageManager.getPackageInfo(packageName, 0).lastUpdateTime
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

val Context.isInstallFromUpdate: Boolean
    get() = firstInstallTime != lastUpdateTime

fun Context.hideKeyboard(windowToken: IBinder) = inputManager.hideSoftInputFromWindow(windowToken, 0)

fun Context.showKeyboard(view: View) = inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
fun Context.forceShowKeyboard(view: View) = inputManager.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_FORCED, 0)

fun Context.hasPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
