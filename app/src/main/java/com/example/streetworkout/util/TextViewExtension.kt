package com.example.streetworkout.util

import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.changeColor(color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}
