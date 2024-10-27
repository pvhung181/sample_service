package com.spark.tapbi.sampleservice.service

import android.content.Intent
import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi
import com.spark.tapbi.sampleservice.ui.incoming.IncomingCallActivity

@RequiresApi(Build.VERSION_CODES.N)
class MyCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val callResponseBuilder = CallResponse.Builder()
        val intent = Intent(this, IncomingCallActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        respondToCall(callDetails, callResponseBuilder.build())
    }
}