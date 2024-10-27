package com.spark.tapbi.sampleservice.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.telephony.TelephonyManager
import com.spark.tapbi.sampleservice.common.models.Counter
import com.spark.tapbi.sampleservice.receiver.IncomingCallReceiver
import com.spark.tapbi.sampleservice.utils.NotificationHelper
import com.spark.tapbi.sampleservice.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyNotificationService : Service() {

    private val counter = Counter()
    private var isRunning = false
    private lateinit var receiver: IncomingCallReceiver

    override fun onCreate() {
        super.onCreate()
        receiver = IncomingCallReceiver()
        val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START_SERVICE" -> {
                if(!isRunning) {
                    startAsForeground()
                    isRunning = true
                }
            }
            else -> stopService()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startAsForeground() {
        if (Utils.isAtLeastSdkVersion(Build.VERSION_CODES.O))
            NotificationHelper.createNotificationChannel(this)
        CoroutineScope(Dispatchers.Default).launch {
            counter.start().collect {
                notification(it)
            }
        }

    }

    private fun notification(value: Long) {
        val notification = NotificationHelper.buildNotification(this, value)
        startForeground(1, notification)
    }

    private fun stopService() {
        stopSelf()
        counter.stop()
        isRunning = false
    }
}