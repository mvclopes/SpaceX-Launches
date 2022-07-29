package com.mvclopes.spacexlaunches.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Launch(
    val flightNumber: Int,
    val missionName: String,
    val launchYear: String,
    val rocket: Rocket,
    val launchSuccess: Boolean,
    val links: Links,
    val details: String
): Parcelable
