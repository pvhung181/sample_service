package com.spark.tapbi.sampleservice.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.tapbi.spark.sampleService.R

object NotificationHelper {
    private const val NOTIFICATION_CHANNEL_ID = "general_notification_channel"

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Sample notification",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            setShowBadge(true)
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun buildNotification(context: Context, value: Long): Notification {
        var builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Service")
            .setContentText("Running.... : $value")
            .setSound(null)
            .setSilent(true)
            .setOngoing(true)
            .setAutoCancel(false)

        return builder.build()
    }

}