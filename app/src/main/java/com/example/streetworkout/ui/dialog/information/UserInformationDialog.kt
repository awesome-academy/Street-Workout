package com.example.streetworkout.ui.dialog.information

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.streetworkout.R
import com.example.streetworkout.data.resource.entity.User
import com.example.streetworkout.databinding.DialogFragmentUserInformationBinding
import com.example.streetworkout.util.PatternConst
import com.example.streetworkout.util.observableInputNumber
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.dialog_fragment_user_information.*
import kotlinx.android.synthetic.main.fragment_user_information.editTextAge
import kotlinx.android.synthetic.main.fragment_user_information.editTextHeight
import kotlinx.android.synthetic.main.fragment_user_information.editTextWeight
import kotlinx.android.synthetic.main.fragment_user_information.textInputLayoutAge
import kotlinx.android.synthetic.main.fragment_user_information.textInputLayoutHeight
import kotlinx.android.synthetic.main.fragment_user_information.textInputLayoutWeight
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class UserInformationDialog : DialogFragment(), RadioGroup.OnCheckedChangeListener {
    private val viewModel by viewModel<InformationViewModel>()
    private lateinit var binding: DialogFragmentUserInformationBinding
    private var sex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_fragment_user_information,
                container,
                false
            )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initAction()
    }

    override fun onCheckedChanged(group: RadioGroup?, id: Int) {
        when (id) {
            R.id.radioButtonMale -> sex = 1
            R.id.radioButtonFemale -> sex = 2
        }
    }

    private fun observeData() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.user = it
            sex = it.sex
        })
    }

    private fun initAction() {
        controlEditTextStream()
        materialButtonClose.setOnClickListener { this.dismiss() }
        materialButtonUpdate.setOnClickListener { updateUserInformation() }
        radioGroup.setOnCheckedChangeListener(this)
    }

    private fun controlEditTextStream() {
        editTextAge.observableInputNumber(
            10,
            100,
            getString(R.string.error_invalid_age),
            textInputLayoutAge
        ).subscribe()

        editTextWeight.observableInputNumber(
            20,
            200,
            getString(R.string.error_invalid_weight),
            textInputLayoutWeight
        ).subscribe()

        editTextHeight.observableInputNumber(
            100,
            300,
            getString(R.string.error_invalid_height),
            textInputLayoutHeight
        ).subscribe()
    }

    private fun updateUserInformation() {
        val age = editTextAge.text.toString()
        val weight = editTextWeight.text.toString()
        val height = editTextHeight.text.toString()

        if (age.isEmpty() || weight.isEmpty() || height.isEmpty()) {
            context?.showToast(getString(R.string.error_edit_text_empty))
            return
        }
        if (textInputLayoutAge.error == null
            && textInputLayoutWeight.error == null
            && textInputLayoutHeight.error == null
        ) {
            val value = weight.toDouble().div(height.toDouble().div(100).times(2))
            val bmi =
                DecimalFormat(PatternConst.PATTERN_DOUBLE).format(value).replace(",", ".")

            viewModel.updateUserInformation(User(1, sex, age.toInt(), weight.toInt(), height.toInt(), bmi.toDouble()))
            context?.showToast(getString(R.string.msg_update_user_information_success))
            this.dismiss()
        }
    }
}
