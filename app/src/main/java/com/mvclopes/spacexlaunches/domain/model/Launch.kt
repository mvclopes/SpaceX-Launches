package com.mvclopes.spacexlaunches.domain.model

data class Launch(
    val flightNumber: Int,
    val missionName: String,
    val launchYear: String,
    val rocket: Rocket,
    val launchSuccess: Boolean,
    val links: Links,
    val details: String
)
