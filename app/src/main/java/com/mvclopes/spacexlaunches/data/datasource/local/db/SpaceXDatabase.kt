package com.mvclopes.spacexlaunches.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvclopes.spacexlaunches.data.datasource.local.dao.SpaceXDao
import com.mvclopes.spacexlaunches.data.datasource.local.model.LaunchEntity

@Database(entities = [LaunchEntity::class], version = 1)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract fun spaceXDAO(): SpaceXDao
}

private lateinit var INSTANCE: SpaceXDatabase

fun getDataBase(context: Context): SpaceXDatabase {
    synchronized(SpaceXDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SpaceXDatabase::class.java,
                "space_x_launches_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
