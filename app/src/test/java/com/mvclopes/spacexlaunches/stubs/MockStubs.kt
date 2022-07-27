package com.mvclopes.spacexlaunches.stubs

import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.LinksEntity
import com.mvclopes.spacexlaunches.data.datasource.local.model.RocketEntity
import com.mvclopes.spacexlaunches.data.model.LaunchResponse
import com.mvclopes.spacexlaunches.data.model.LinksResponse
import com.mvclopes.spacexlaunches.data.model.RocketResponse
import com.mvclopes.spacexlaunches.domain.model.Launch
import com.mvclopes.spacexlaunches.domain.model.Links
import com.mvclopes.spacexlaunches.domain.model.Rocket

fun getLaunchesResponseStub() = listOf(
    LaunchResponse(
        flightNumber = 1,
        missionName = "Falcon Sat",
        launchSuccess = true,
        launchYear = "2010",
        rocket = RocketResponse(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = LinksResponse(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    ),
    LaunchResponse(
        flightNumber = 2,
        missionName = "Trailblazer",
        launchSuccess = true,
        launchYear = "2011",
        rocket = RocketResponse(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = LinksResponse(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    )
)

fun getLaunchesEntityStub() = listOf(
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

fun getLaunchesDomainStub() = listOf(
    Launch(
        flightNumber = 1,
        missionName = "Falcon Sat",
        launchSuccess = true,
        launchYear = "2010",
        rocket = Rocket(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = Links(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    ),
    Launch(
        flightNumber = 2,
        missionName = "Trailblazer",
        launchSuccess = true,
        launchYear = "2011",
        rocket = Rocket(
            rocketName = "Falcon 1",
            rocketType = "type 1"
        ),
        links = Links(
            missionPatch = "missionPatch",
            missionPatchSmall = "missionPatchSmall",
            article = "article",
            video = "video"
        ),
        details = "details launch"
    )
)

fun getLaunchDomainStub(): Launch = getLaunchesDomainStub().first()

fun getLaunchEntityStub(): LaunchEntity = getLaunchesEntityStub().first()

fun getFlightNumberStub(): Int = getLaunchEntityStub().flightNumber
