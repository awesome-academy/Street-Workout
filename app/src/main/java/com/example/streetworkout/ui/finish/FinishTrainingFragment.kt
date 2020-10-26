package com.example.streetworkout.ui.finish

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.ui.training.ShareTrainingViewModel
import com.example.streetworkout.util.TimeConst.FORMAT_TIME_EXERCISE
import kotlinx.android.synthetic.main.fragment_finish_training.*
import java.text.SimpleDateFormat
import java.util.*

class FinishTrainingFragment : BaseFragment() {
    private val navArgs by navArgs<FinishTrainingFragmentArgs>()
    private val shareViewModel by activityViewModels<ShareTrainingViewModel>()

    override val layoutResources: Int
        get() = R.layout.fragment_finish_training

    override fun initData() {
        shareViewModel.exercises.observe(viewLifecycleOwner, Observer {
            textViewTotalExercise.text = it.size.toString()
            textViewTotalTime.text =
                SimpleDateFormat(FORMAT_TIME_EXERCISE, Locale.getDefault()).format(navArgs.bundleTotalTime * 1000)
            textViewTotalKcal.text = navArgs.bundleTotalKcal.toString()
        })
    }

    override fun initAction() {}
}
