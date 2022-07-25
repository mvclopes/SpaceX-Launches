package com.mvclopes.spacexlaunches.data.datasource.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksEntity(
    val missionPatch: String,
    val missionPatchSmall: String,
    val article: String,
    val video: String,
) : Parcelable
