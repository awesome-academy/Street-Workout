package com.example.streetworkout.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.streetworkout.R
import com.example.streetworkout.util.LinkConst.URL_GIF_IMAGE
import com.example.streetworkout.util.LinkConst.URL_IMAGE

fun ImageView.loadImage(image: String) {
    Glide.with(this)
        .load(URL_IMAGE + image)
        .placeholder(R.drawable.image_not_avaiable)
        .into(this)
}

fun ImageView.loadGifImage(image: String) {
    Glide.with(this)
        .asGif()
        .load(URL_GIF_IMAGE + image)
        .placeholder(R.drawable.image_not_avaiable)
        .into(this)
}
