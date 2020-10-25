package com.example.streetworkout.ui.training

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.databinding.FragmentTrainingBinding
import com.example.streetworkout.util.TimeConst.FORMAT_CURRENT_DATE
import com.example.streetworkout.util.TimeConst.FORMAT_TIME_EXERCISE
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_training.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class TrainingFragment : BaseFragment() {

    private val argsNav by navArgs<TrainingFragmentArgs>()
    private val shareViewModel by activityViewModels<ShareTrainingViewModel>()
    private val trainingViewModel by viewModel<TrainingViewModel>()
    private var binding: FragmentTrainingBinding? = null
    private var isStreamExist = false

    override val layoutResources: Int
        get() = R.layout.fragment_training

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrainingBinding.bind(view)
    }

    override fun initData() {
        shareViewModel.exercises.observe(viewLifecycleOwner, Observer {
            trainingViewModel.setListExercise(it)
            if (!isStreamExist) trainingViewModel.startTimeTraining(0)
        })
        shareViewModel.position.observe(viewLifecycleOwner, Observer(trainingViewModel::setPosition))
        observeData()
    }

    override fun initAction() {
        materialButtonCheck.setOnClickListener { trainingViewModel.openFragment() }
    }

    override fun onResume() {
        super.onResume()
        if (!isStreamExist) trainingViewModel.resumeStream()
        isStreamExist = false
    }

    override fun onStop() {
        if (!isStreamExist) trainingViewModel.stopStream()
        super.onStop()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun observeData() = with(trainingViewModel) {
        exercise.observe(viewLifecycleOwner, Observer {
            binding?.exercise = it
            countDownTime(it.time)
        })

        isExistExercise.observe(viewLifecycleOwner, Observer {
            it?.let { isExistExercise ->
                if (isExistExercise) {
                    findNavController().navigate(R.id.actionToRestTimeFragment)
                    isStreamExist = true
                } else {
                    trainingViewModel.finishTraining()
                }
            }
        })

        totalTimeTraining.observe(viewLifecycleOwner, Observer {
            it?.let {
                trainingViewModel.addHistoryTraining(Training(
                    null,
                    argsNav.bundleNameExercise.toString(),
                    getMuscleGroupImage(),
                    it,
                    0,
                    SimpleDateFormat(FORMAT_CURRENT_DATE, Locale.getDefault()).format(argsNav.bundleTimeStart))
                )
                findNavController().navigate(TrainingFragmentDirections.actionToFinishTrainingFragment(it))
            }
        })

        countDownTimeExercise.observe(viewLifecycleOwner, Observer {
            textViewTime.text = SimpleDateFormat(FORMAT_TIME_EXERCISE, Locale.getDefault()).format(it * 1000)
        })

        error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }

    private fun countDownTime(time: String) {
        if (!time.contains("x")) {
            trainingViewModel.countDownTimerExercise(time.substring(time.length - 2, time.length).toLong())
        }
    }

    private fun getMuscleGroupImage(): Int = when (argsNav.bundleIdExercise) {
        2, 8, 13 -> R.drawable.abs
        3, 9, 14 -> R.drawable.chest
        4, 10, 15 -> R.drawable.leg
        5, 11, 16 -> R.drawable.arm
        6, 12, 17 -> R.drawable.back
        1 -> R.drawable.full
        else -> 0
    }
}
