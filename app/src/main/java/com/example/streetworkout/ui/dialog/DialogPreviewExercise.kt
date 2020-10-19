package com.example.streetworkout.ui.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.streetworkout.R
import com.example.streetworkout.databinding.DialogFragmentPreviewExerciseBinding
import kotlinx.android.synthetic.main.dialog_fragment_preview_exercise.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogPreviewExercise : DialogFragment() {

    private val viewModel by viewModel<DialogPreviewExerciseViewModel>()
    private val navArgs by navArgs<DialogPreviewExerciseArgs>()
    private lateinit var binding: DialogFragmentPreviewExerciseBinding
    private var size = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_preview_exercise, container, false)
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

        binding.viewModel = viewModel
        binding.dialog = this
        initData()
        observeData()
    }

    private fun initData() {
        viewModel.setPosition(navArgs.bundlePosition)
        viewModel.setListExercise(navArgs.bundleListExercise)
        size = navArgs.bundleListExercise.size
    }

    private fun observeData() {
        viewModel.exercise.observe(viewLifecycleOwner, Observer {
            binding.exercise = it
        })

        var currentPage: Int
        viewModel.currentPage.observe(viewLifecycleOwner, Observer {
            currentPage = it
            textViewPageNumber.text = getString(R.string.text_current_page, ++currentPage, size)
            checkPosition(it)
        })
    }

    private fun checkPosition(position: Int) {
        when (position) {
            0 -> imageViewPrevious.visibility = View.INVISIBLE
            (size - 1) -> imageViewNext.visibility = View.INVISIBLE
            else -> {
                imageViewPrevious.visibility = View.VISIBLE
                imageViewNext.visibility = View.VISIBLE
            }
        }
    }
}
