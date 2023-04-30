package app.moviebase.androidx.content

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.buildChannel(
    id: String,
    name: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    val channel = NotificationChannel(id, name, importance)
    channel.enableVibration(true)
    channel.enableLights(true)
    createNotificationChannel(channel)
}
