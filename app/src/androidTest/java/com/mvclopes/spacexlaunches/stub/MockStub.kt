package com.mvclopes.spacexlaunches.stub

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.LinksEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.RocketEntity

fun getEntityLaunchListStub() = listOf(
    LaunchEntity(
        flightNumber = 1,
        missionName = "Falcon Sat",
        launchSuccess = true,
        launchYear = "2010",
        rocket = RocketEntity(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = LinksEntity(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    ),
    LaunchEntity(
        flightNumber = 2,
        missionName = "Trailblazer",
        launchSuccess = true,
        launchYear = "2011",
        rocket = RocketEntity(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = LinksEntity(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    )
)

fun getEntityLaunchStub(): LaunchEntity = getEntityLaunchListStub().first()
