package com.example.streetworkout.ui.rest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.databinding.FragmentRestTimeBinding
import com.example.streetworkout.ui.training.ShareTrainingViewModel
import com.example.streetworkout.util.TimeConst.FORMAT_TIME_EXERCISE
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_rest_time.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class RestTimeFragment : BaseFragment() {

    private val shareViewModel by activityViewModels<ShareTrainingViewModel>()
    private val restViewModel by viewModel<RestTimeViewModel>()
    private var binding: FragmentRestTimeBinding? = null

    override val layoutResources: Int
        get() = R.layout.fragment_rest_time

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRestTimeBinding.bind(view)
        binding?.viewModel = restViewModel
    }

    override fun initData() {
        shareViewModel.exercises.observe(viewLifecycleOwner, Observer(restViewModel::setListExercise))
        shareViewModel.position.observe(viewLifecycleOwner, Observer(restViewModel::setCurrentPosition))
        restViewModel.countDownTimerRest(30)
        observeData()
    }

    override fun initAction() {}

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun observeData() = with(restViewModel) {
        currentPosition.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
            shareViewModel.setPosition(it)
        })

        countDownTimeRest.observe(viewLifecycleOwner, Observer {
            textViewTime.text = SimpleDateFormat(FORMAT_TIME_EXERCISE, Locale.getDefault()).format(it * 1000)
        })

        isRunComplete.observe(viewLifecycleOwner, Observer {
            if (it) restViewModel.skipRestTimeTraining()
        })

        exercise.observe(viewLifecycleOwner, Observer {
            binding?.exercise = it
        })

        textCurrentPage.observe(viewLifecycleOwner, Observer {
            textViewPage.text = it
        })

        error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }
}
