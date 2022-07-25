package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.LinksEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.RocketEntity
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.model.Links
import com.mvclopes.spacexlaunches.domain.model.Rocket

fun List<LaunchEntity>.toDomain(): List<Launch> = this.map { it.toDomain() }

fun List<Launch>.toEntity(): List<LaunchEntity> = this.map { it.toEntity() }

fun Launch.toEntity() = LaunchEntity(
    flightNumber = flightNumber,
    missionName = missionName,
    launchYear = launchYear,
    rocket = rocket.toEntity(),
    launchSuccess = launchSuccess,
    links = links.toEntity(),
    details = details
)

fun LaunchEntity.toDomain() = Launch(
    flightNumber = flightNumber,
    missionName = missionName,
    launchYear = launchYear,
    rocket = rocket.toDomain(),
    launchSuccess = launchSuccess,
    links = links.toDomain(),
    details = details
)

private fun RocketEntity.toDomain() = Rocket(
    rocketName =  rocketName,
    rocketType = rocketType
)

private fun Rocket.toEntity() = RocketEntity(
    rocketName =  rocketName,
    rocketType = rocketType
)

private fun LinksEntity.toDomain() = Links(
    missionPatch = missionPatch,
    missionPatchSmall = missionPatchSmall,
    article = article,
    video = video
)

private fun Links.toEntity() = LinksEntity(
    missionPatch = missionPatch,
    missionPatchSmall = missionPatchSmall,
    article = article,
    video = video
)
