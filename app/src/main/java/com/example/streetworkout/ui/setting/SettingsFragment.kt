package com.example.streetworkout.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.streetworkout.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting_screen, rootKey)
    }
}
