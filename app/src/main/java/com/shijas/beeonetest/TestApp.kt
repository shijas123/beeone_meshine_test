package com.shijas.beeonetest

import android.app.Application
import com.shijas.beeonetest.common.applyTheme
import com.shijas.beeonetest.settings.domain.SharedPreferenceRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TestApp : Application() {
    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    override fun onCreate() {
        super.onCreate()
        applyTheme(sharedPreferenceRepository.getThemType())
    }
}