package com.mvclopes.spacexlaunches.data.model

import com.google.gson.annotations.SerializedName

data class LaunchResponse(
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val missionName: String,
    @SerializedName("launch_year") val launchYear: String,
    @SerializedName("rocket") val rocket: RocketResponse,
    @SerializedName("launch_success") val launchSuccess: Boolean,
    @SerializedName("links") val links: LinksResponse,
    @SerializedName("details") val details: String
)
