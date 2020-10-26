package com.example.streetworkout.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.model.GroupMuscle
import com.example.streetworkout.util.TimeConst.FORMAT_TOTAL_TIME_TRAINING
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment() {

    private val challengeAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val beginAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val normalAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val advanceAdapter = HomeAdapter(::openDetailExerciseFragment)
    private val homeViewModel by viewModel<HomeViewModel>()

    override val layoutResources: Int
        get() = R.layout.fragment_home

    override fun initData() {
        initAdapter()
        homeViewModel.getData()
        homeViewModel.getTrainingHistoryInformation()
        observeData()
    }

    override fun initAction() {
        constraintViewHistory.setOnClickListener { openTrainingHistoryFragment() }
    }

    private fun initAdapter() {
        recyclerViewChallenge.adapter = challengeAdapter
        recyclerViewMuscleBegin.adapter = beginAdapter
        recyclerViewMuscleNormal.adapter = normalAdapter
        recyclerViewMuscleAdvance.adapter = advanceAdapter
    }

    private fun openTrainingHistoryFragment() {
        findNavController().navigate(HomeFragmentDirections.actionToTrainingHistoryFragment())
    }

    private fun openDetailExerciseFragment(item: GroupMuscle, position: Int) = with(item) {
        findNavController().navigate(
            HomeFragmentDirections.actionToDetailExerciseFragment(title, image, id, levelTraining.toFloat())
        )
    }

    private fun observeData() = with(homeViewModel) {
        categories.observe(viewLifecycleOwner, Observer {
            challengeAdapter.submitList(it.challenge)
            beginAdapter.submitList(it.beginner)
            normalAdapter.submitList(it.normal)
            advanceAdapter.submitList(it.advance)
        })

        historyInformation.observe(viewLifecycleOwner, Observer {
            textViewTotalTraining.text = it.first.toString()
            textViewTotalKcal.text = it.second.toString()
            textViewTotalTime.text =
                SimpleDateFormat(FORMAT_TOTAL_TIME_TRAINING, Locale.getDefault()).format(it.third * 1000)
        })

        error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }
}
