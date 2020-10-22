package com.example.streetworkout.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.streetworkout.R
import com.example.streetworkout.util.loadGifImage
import com.example.streetworkout.util.loadImage
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageUrl")
fun loadImage(imageView: ShapeableImageView, url: String) {
    imageView.loadImage(url)
}

@BindingAdapter("level")
fun updateImage(imageView: ImageView, level: Int) {
    when (level) {
        1 -> imageView.setImageResource(R.drawable.ic_baseline_flash_off_24)
        3 -> imageView.setImageResource(R.drawable.ic_baseline_flash_on_24)
    }
}

@BindingAdapter("gifImage")
fun loadGifImage(imageView: ImageView, url: String) {
    imageView.loadGifImage(url)
}
