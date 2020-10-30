package com.example.streetworkout.ui.user

import android.content.Context
import android.os.Bundle
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.resource.entity.User
import com.example.streetworkout.ui.BottomNavigationListener
import com.example.streetworkout.ui.dialog.bmi.DialogBmi
import com.example.streetworkout.util.PatternConst.PATTERN_DOUBLE
import com.example.streetworkout.util.changeColor
import com.example.streetworkout.util.observableInputNumber
import com.example.streetworkout.util.showToast
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.android.synthetic.main.fragment_user_information.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class UserInformationFragment : BaseFragment(), RadioGroup.OnCheckedChangeListener {
    private var bottomNavigationListener: BottomNavigationListener? = null
    private val viewModel by viewModel<UserInformationViewModel>()
    private var sex = 1

    override val layoutResources: Int
        get() = R.layout.fragment_user_information

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) bottomNavigationListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkUserExist()
    }

    override fun initData() {
        observeData()
    }

    override fun initAction() {
        controlEditTextStream()
        buttonStart.setOnClickListener { openHomeFragment() }
        imageViewHelp.setOnClickListener { context?.let { DialogBmi(it).show() } }
    }

    override fun onCheckedChanged(group: RadioGroup?, id: Int) {
        when (id) {
            R.id.radioButtonMale -> sex = 1
            R.id.radioButtonFemale -> sex = 2
        }
    }

    private fun observeData() = with(viewModel) {
        error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })

        existUser.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(UserInformationFragmentDirections.actionToNavigationHome())
            bottomNavigationListener?.showBottomNav()
        })
    }

    private fun controlEditTextStream() {
        editTextAge.observableInputNumber(
            10,
            100,
            getString(R.string.error_invalid_age),
            textInputLayoutAge
        ).subscribe()

        val observableEditTextWeight =
            editTextWeight.observableInputNumber(
                20,
                200,
                getString(R.string.error_invalid_weight),
                textInputLayoutWeight
            )

        val observableEditTextHeight =
            editTextHeight.observableInputNumber(
                100,
                300,
                getString(R.string.error_invalid_height),
                textInputLayoutHeight
            )

        Observable.combineLatest(
            observableEditTextWeight,
            observableEditTextHeight,
            BiFunction<String, String, String> { weight, height ->
                val value = weight.toDouble().div(height.toDouble().div(100).times(2))
                DecimalFormat(PATTERN_DOUBLE).format(value).replace(",", ".")
            }
        ).subscribe({
            showBmiValue(it)
        }, {
            context?.showToast(it.message.toString())
        })
    }

    private fun showBmiValue(value: String) {
        when (value.toDouble()) {
            in 0.0..18.4 -> updateText(R.color.colorPrimary, getString(R.string.msg_bmi_under_weight))
            in 18.5..24.9 -> updateText(android.R.color.holo_green_dark, getString(R.string.msg_bmi_normal))
            in 25.0..29.9 -> updateText(android.R.color.holo_orange_light, getString(R.string.msg_bmi_over_weight))
            in 30.0..34.9 -> updateText(android.R.color.holo_orange_dark, getString(R.string.msg_bmi_obese))
            else -> updateText(android.R.color.holo_red_dark, getString(R.string.msg_bmi_extremely_obese))
        }
        textViewBmiValue.text = value
    }

    private fun updateText(color: Int, message: String) {
        textViewBmiValue.changeColor(color)
        textViewBmiContent.changeColor(color)
        textViewBmiContent.text = message
    }

    private fun openHomeFragment() {
        val age = editTextAge.text.toString()
        val weight = editTextWeight.text.toString()
        val height = editTextHeight.text.toString()
        val bmi = textViewBmiValue.text.toString().toDouble()

        if (age.isEmpty() || weight.isEmpty() || height.isEmpty()) {
            context?.showToast(getString(R.string.error_edit_text_empty))
            return
        }
        if (textInputLayoutAge.error == null
            && textInputLayoutWeight.error == null
            && textInputLayoutHeight.error == null
        ) {
            viewModel.addUser(User(null, sex, age.toInt(), weight.toInt(), height.toInt(), bmi))
            findNavController().navigate(UserInformationFragmentDirections.actionToNavigationHome())
            bottomNavigationListener?.showBottomNav()
        }
    }
}
