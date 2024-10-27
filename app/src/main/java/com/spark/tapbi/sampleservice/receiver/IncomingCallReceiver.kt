package com.spark.tapbi.sampleservice.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.spark.tapbi.sampleservice.App
import com.spark.tapbi.sampleservice.service.MyNotificationService
import com.spark.tapbi.sampleservice.ui.incoming.IncomingCallActivity

class IncomingCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Intent(App.instance, MyNotificationService::class.java).also {
                    it.action = "START_SERVICE"
                    context.startService(it)
                }
                val i = Intent(context, IncomingCallActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
        }
    }
}