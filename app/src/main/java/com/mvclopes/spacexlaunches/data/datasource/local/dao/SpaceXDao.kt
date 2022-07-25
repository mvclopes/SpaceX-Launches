package com.mvclopes.spacexlaunches.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity

@Dao
interface SpaceXDao {

    @Query("SELECT * FROM tb_space_x_launches")
    suspend fun getAllLaunches(): List<LaunchEntity>

    @Insert(onConflict = ABORT)
    suspend fun insertAll(launches: List<LaunchEntity>)

    @Insert(onConflict = ABORT)
    suspend fun insertLaunch(launch: LaunchEntity)

    @Query("SELECT * FROM tb_space_x_launches WHERE flight_number LIKE :flightNumber")
    suspend fun getSpecificLaunch(flightNumber: Int): LaunchEntity

    @Delete
    suspend fun deleteLaunch(launch: LaunchEntity)

}
