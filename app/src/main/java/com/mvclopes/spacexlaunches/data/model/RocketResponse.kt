package com.mvclopes.spacexlaunches.data.model

import com.google.gson.annotations.SerializedName

data class RocketResponse(
    @SerializedName("rocket_name") val rocketName: String,
    @SerializedName("rocket_type") val rocketType: String,
)
