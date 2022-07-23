package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import com.mvclopes.spacexlaunches.data.model.LinksResponse
import com.mvclopes.spacexlaunches.data.model.RocketResponse
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.model.Links
import com.mvclopes.spacexlaunches.domain.model.Rocket

fun List<LaunchResponse>.toDomain(): List<Launch> = this.map { it.toDomain() }

private fun LaunchResponse.toDomain() = Launch(
    flightNumber = flightNumber,
    missionName = missionName,
    launchYear = launchYear,
    rocket = rocket.toDomain(),
    launchSuccess = launchSuccess,
    links = links.toDomain(),
    details = details
)

private fun RocketResponse.toDomain() = Rocket(
    rocketName =  rocketName,
    rocketType = rocketType
)

private fun LinksResponse.toDomain() = Links(
    missionPatch = missionPatch,
    article = article,
    video = video
)
