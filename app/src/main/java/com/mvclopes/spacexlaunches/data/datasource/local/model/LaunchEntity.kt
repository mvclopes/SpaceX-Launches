package com.mvclopes.spacexlaunches.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_space_x_launches")
data class LaunchEntity(
    @PrimaryKey
    @ColumnInfo(name = "flight_number") val flightNumber: Int,
    @ColumnInfo(name = "mission_name") val missionName: String,
    @ColumnInfo(name = "launch_year") val launchYear: String,
    @Embedded val rocket: RocketEntity,
    @ColumnInfo(name = "launch_success") val launchSuccess: Boolean,
    @Embedded val links: LinksEntity,
    val details: String
)
