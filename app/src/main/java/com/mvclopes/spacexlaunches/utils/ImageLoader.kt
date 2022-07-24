package com.mvclopes.spacexlaunches.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mvclopes.spacexlaunches.R

fun loadImage(view: ImageView, url: String) {
    Glide
        .with(view)
        .load(url)
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_broken_image)
        .into(view)
}
