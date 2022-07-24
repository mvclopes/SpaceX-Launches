package com.mvclopes.spacexlaunches.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rocket(
    val rocketName: String,
    val rocketType: String,
) : Parcelable
