package com.spark.tapbi.sampleservice.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.util.Util
import com.spark.tapbi.sampleservice.App
import com.spark.tapbi.sampleservice.service.MyNotificationService
import com.spark.tapbi.sampleservice.ui.base.BaseBindingActivity
import com.spark.tapbi.sampleservice.utils.Utils
import com.tapbi.spark.sampleService.R
import com.tapbi.spark.sampleService.databinding.ActivityMainBinding
import java.security.Permission
import java.security.Permissions

class MainActivity : BaseBindingActivity<ActivityMainBinding, MainViewModel>() {

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->

    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if(Utils.isAtLeastSdkVersion(Build.VERSION_CODES.TIRAMISU))
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG
        ), 1)

        if(Utils.isAtLeastSdkVersion(26)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ANSWER_PHONE_CALLS)  , 2)
        }


        eventClick()
    }

    private fun eventClick() {
        binding?.startService?.setOnClickListener {
            Intent(App.instance, MyNotificationService::class.java).also {
                it.action = "START_SERVICE"
                startService(it)
            }
        }

        binding?.stopService?.setOnClickListener {
            Intent(App.instance, MyNotificationService::class.java).also {
                it.action = "STOP_SERVICE"
                startService(it)
            }
        }

    }


    override fun setupData() {}

}