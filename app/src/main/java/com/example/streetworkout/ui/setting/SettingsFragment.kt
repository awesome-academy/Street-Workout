package com.example.streetworkout.ui.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.streetworkout.R
import com.example.streetworkout.ui.dialog.bmi.DialogBmi
import com.example.streetworkout.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {
    private val settingsViewModel by viewModel<SettingsViewModel>()
    private var age: Preference? = null
    private var sex: Preference? = null
    private var height: Preference? = null
    private var weight: Preference? = null
    private var bmi: Preference? = null
    private var reset: Preference? = null
    private var notification: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting_screen, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
        initAction()
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference) {
            bmi -> context?.let { DialogBmi(it).show() }
            age, sex, height, weight -> openDialogUserInformation()
            reset -> settingsViewModel.resetTrainingHistory()
        }
        return true
    }

    private fun initView() {
        age = findPreference(AGE)
        sex = findPreference(SEX)
        height = findPreference(HEIGHT)
        weight = findPreference(WEIGHT)
        bmi = findPreference(BMI)
        reset = findPreference(RESET)
        notification = findPreference(NOTIFICATION)
    }

    private fun initAction() {
        bmi?.onPreferenceClickListener = this
        age?.onPreferenceClickListener = this
        sex?.onPreferenceClickListener = this
        weight?.onPreferenceClickListener = this
        height?.onPreferenceClickListener = this
        reset?.onPreferenceClickListener = this
    }

    private fun observeData() = with(settingsViewModel) {
        user.observe(viewLifecycleOwner, Observer {
            age?.summary = it.age.toString()
            bmi?.summary = it.bmi.toString()
            height?.summary = getString(R.string.text_user_height_information, it.height)
            weight?.summary = getString(R.string.text_user_weight_information, it.weight)
            if (it.sex == 1) sex?.summary = MALE else sex?.summary = FEMALE
        })

        deleteSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
                context?.showToast(getString(R.string.msg_reset_history_success))
                findNavController().navigate(SettingsFragmentDirections.actionToNavigationHome())
            }
        })
    }

    private fun openDialogUserInformation() {
        findNavController().navigate(
            SettingsFragmentDirections.actionToDialogUserInformation()
        )
    }

    companion object {
        private const val AGE = "age"
        private const val MALE = "Nam"
        private const val FEMALE = "Nữ"
        private const val SEX = "sex"
        private const val WEIGHT = "weight"
        private const val HEIGHT = "height"
        private const val BMI = "bmi"
        private const val RESET = "reset"
        private const val NOTIFICATION = "notification"
    }
}
