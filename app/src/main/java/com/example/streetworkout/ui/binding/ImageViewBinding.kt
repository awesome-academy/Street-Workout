package com.example.streetworkout.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.streetworkout.R
import com.example.streetworkout.util.LinkConst.URL_IMAGE
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageUrl")
fun loadImage(imageView: ShapeableImageView, url: String) {
    Glide.with(imageView.context)
        .load(URL_IMAGE + url)
        .placeholder(R.drawable.logo_app)
        .into(imageView)
}

@BindingAdapter("level")
fun updateImage(imageView: ImageView, level: Int) {
    when (level) {
        1 -> imageView.setImageResource(R.drawable.ic_baseline_flash_off_24)
        3 -> imageView.setImageResource(R.drawable.ic_baseline_flash_on_24)
    }
}
