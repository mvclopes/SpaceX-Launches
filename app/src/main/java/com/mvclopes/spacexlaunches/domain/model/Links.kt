package com.mvclopes.spacexlaunches.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val missionPatch: String,
    val article: String,
    val video: String,
) : Parcelable
