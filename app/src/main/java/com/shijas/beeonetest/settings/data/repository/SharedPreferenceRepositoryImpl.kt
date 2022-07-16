package com.shijas.beeonetest.settings.data.repository

import android.content.SharedPreferences
import com.shijas.beeonetest.common.Constants
import com.shijas.beeonetest.common.Constants.DEFAULT
import com.shijas.beeonetest.common.Constants.THEME_KEY
import com.shijas.beeonetest.settings.domain.SharedPreferenceRepository
import javax.inject.Inject

class SharedPreferenceRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): SharedPreferenceRepository {
    override fun getThemType(): String {
        return sharedPreferences.getString(THEME_KEY, DEFAULT) ?: DEFAULT
    }


}