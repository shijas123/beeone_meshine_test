package com.shijas.beeonetest.common

import androidx.appcompat.app.AppCompatDelegate
import com.shijas.beeonetest.common.Constants.DARK
import com.shijas.beeonetest.common.Constants.DEFAULT
import com.shijas.beeonetest.common.Constants.LIGHT

fun applyTheme(theme: String?){
    when(theme){

        LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        DEFAULT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    }
}