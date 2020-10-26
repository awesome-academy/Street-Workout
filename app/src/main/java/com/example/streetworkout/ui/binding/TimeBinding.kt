package com.example.streetworkout.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.streetworkout.util.TimeConst.FORMAT_TIME_EXERCISE
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formatTime")
fun formatTimeExercise(textView: TextView, time: Int) {
    textView.text = SimpleDateFormat(FORMAT_TIME_EXERCISE, Locale.getDefault()).format(time * 1000)
}
