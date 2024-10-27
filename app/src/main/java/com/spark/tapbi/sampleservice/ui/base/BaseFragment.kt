package com.spark.tapbi.sampleservice.ui.base

import androidx.fragment.app.Fragment
import com.spark.tapbi.sampleservice.data.local.SharedPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var mPrefHelper: SharedPreferenceHelper
}