package com.mvclopes.spacexlaunches.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mvclopes.spacexlaunches.R

private const val STROKE_WIDTH = 5f
private const val CENTER_RADIUS = 120f

fun loadImage(view: ImageView, url: String) {
    Glide
        .with(view)
        .load(url)
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_broken_image)
        .into(view)
}

fun loadImageWithProgress(view: ImageView, url: String) {
    val circularProgress = CircularProgressDrawable(view.context)
    circularProgress.strokeWidth = STROKE_WIDTH
    circularProgress.centerRadius = CENTER_RADIUS
    circularProgress.start()

    Glide
        .with(view)
        .load(url)
        .placeholder(circularProgress)
        .error(R.drawable.ic_broken_image)
        .into(view)
}
