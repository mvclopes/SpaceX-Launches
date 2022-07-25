package com.mvclopes.spacexlaunches.data.datasource.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketEntity(
    val rocketName: String,
    val rocketType: String,
) : Parcelable
