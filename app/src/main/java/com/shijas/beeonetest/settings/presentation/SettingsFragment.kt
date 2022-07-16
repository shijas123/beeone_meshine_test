package com.shijas.beeonetest.settings.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.shijas.beeonetest.R
import com.shijas.beeonetest.common.Constants.DEFAULT
import com.shijas.beeonetest.common.Constants.THEME_KEY
import com.shijas.beeonetest.common.applyTheme

class SettingsFragment : PreferenceFragmentCompat() , SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String
    ) {
        if (key == THEME_KEY){
            applyTheme(sharedPreferences?.getString(key,DEFAULT))
        }
    }
    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }
}