package com.example.streetworkout.ui.history

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_history_training.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainingHistoryFragment : BaseFragment() {

    private val historyViewModel by viewModel<TrainingHistoryViewModel>()
    private val historyAdapter = TrainingHistoryAdapter(::openDetailExercise)

    override val layoutResources: Int
        get() = R.layout.fragment_history_training

    override fun initData() {
        recyclerViewHistory.adapter = historyAdapter
        observeData()
    }

    override fun initAction() {
        toolbarHistory.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun observeData() = with(historyViewModel) {
        trainingHistory.observe(viewLifecycleOwner, Observer {
            historyAdapter.submitList(it)
        })

        error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }

    private fun openDetailExercise(training: Training, position: Int) = with(training) {
        findNavController().navigate(
            TrainingHistoryFragmentDirections.actionToDetailExerciseFragment(
                name,
                imageExercise,
                idExercise,
                level.toFloat()
            )
        )
    }
}
