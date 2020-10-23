package com.example.streetworkout.ui.detail

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseFragment
import com.example.streetworkout.data.model.Exercise
import com.example.streetworkout.util.loadImage
import com.example.streetworkout.util.showToast
import kotlinx.android.synthetic.main.fragment_detail_exercises.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailExerciseFragment : BaseFragment() {

    private val navArgs by navArgs<DetailExerciseFragmentArgs>()
    private val viewModel by viewModel<DetailExerciseViewModel>()
    private val adapterExercise = DetailExerciseAdapter(::openDialogPreview, ::onImageTouch)
    private val callback: ItemTouchHelper.Callback = DetailExerciseItemTouch(::onMove)
    private val itemTouchHelper = ItemTouchHelper(callback)

    override val layoutResources: Int
        get() = R.layout.fragment_detail_exercises

    override fun initData() {
        collapsingDetailExercise.title = navArgs.bundleNameExercise
        imageViewPreview.loadImage(navArgs.bundleImageExercise.toString())

        initAdapter()
        viewModel.getListExercise(navArgs.bundleIdExercise)
        observeData()
    }

    override fun initAction() {
        buttonStartTraining.setOnClickListener { startTraining() }
    }

    private fun initAdapter() {
        recyclerViewExercise.adapter = adapterExercise
        itemTouchHelper.attachToRecyclerView(recyclerViewExercise)
    }

    private fun startTraining() {
        findNavController().navigate(
            DetailExerciseFragmentDirections.actionToTrainingFragment(
                navArgs.bundleIdExercise,
                navArgs.bundleNameExercise,
                System.currentTimeMillis()
            )
        )
    }

    private fun openDialogPreview(exercise: Exercise, position: Int) {
        findNavController().navigate(
            DetailExerciseFragmentDirections
                .actionToDialogPreviewExercise(adapterExercise.currentList.toTypedArray(), position)
        )
    }

    private fun onImageTouch(viewHolder: DetailExerciseViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private fun observeData() {
        viewModel.exercise.observe(viewLifecycleOwner, Observer {
            adapterExercise.submitList(it)
            textViewTotalExercise.text = getString(R.string.text_total_exercise, it.size)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            context?.showToast(it)
        })
    }

    private fun onMove(fromPosition: Int, toPosition: Int) {
        viewModel.onMove(fromPosition, toPosition)
        adapterExercise.notifyItemMoved(fromPosition, toPosition)
    }
}
