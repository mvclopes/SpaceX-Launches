package com.mvclopes.spacexlaunches.data.datasource.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mvclopes.spacexlaunches.data.datasource.local.dao.SpaceXDao
import com.mvclopes.spacexlaunches.stub.getEntityLaunchStub
import com.mvclopes.spacexlaunches.stub.getEntityLaunchListStub
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SpaceXDatabaseTest {

    private lateinit var database: SpaceXDatabase
    private lateinit var spaceXDao: SpaceXDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, SpaceXDatabase::class.java
        ).build()
        spaceXDao = database.spaceXDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertLaunch_should_insert_new_favorite_launch_in_database_correctly() = runBlocking {
        // Given
        val entityLaunch = getEntityLaunchStub()

        // When
        spaceXDao.insertLaunch(entityLaunch)
        val result = spaceXDao.getAllLaunches()

        // Then
        assertTrue(result.contains(entityLaunch))
    }

    @Test
    fun deleteLaunch_should_delete_specific_launch_in_database_correctly() = runBlocking {
        // Given
        val entityList = getEntityLaunchListStub()
        val entityLaunch = getEntityLaunchStub()
        val expectedList = listOf(getEntityLaunchListStub().last())

        // When
        entityList.forEach { spaceXDao.insertLaunch(it) }
        spaceXDao.deleteLaunch(entityLaunch)
        val result = spaceXDao.getAllLaunches()

        // Then
        assertFalse(result.contains(entityLaunch))
        assertNotSame(expectedList, result)
    }
}
