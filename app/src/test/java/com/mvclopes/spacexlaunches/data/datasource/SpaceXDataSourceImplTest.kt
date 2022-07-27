package com.mvclopes.spacexlaunches.data.datasource

import com.mvclopes.spacexlaunches.data.datasource.local.LocalDataSource
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.stubs.getFlightNumberStub
import com.mvclopes.spacexlaunches.stubs.getLaunchEntityStub
import com.mvclopes.spacexlaunches.stubs.getLaunchesEntityStub
import com.mvclopes.spacexlaunches.stubs.getLaunchesResponseStub
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class SpaceXDataSourceImplTest {

    private val remoteDataSourceMock = mockk<RemoteDataSource>(relaxed = true)
    private val localDataSourceMock = mockk<LocalDataSource>()
    private val target = SpaceXDataSourceImpl(remoteDataSourceMock, localDataSourceMock)

    @Test
    fun `when getAllLaunches returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getLaunchesResponseStub()
        every { remoteDataSourceMock.getAllLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getAllLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when getFavoriteLaunches returns success should emit a launch entity list`() = runBlocking {
        // Given
        val expectedObject = getLaunchesEntityStub()
        every { localDataSourceMock.getFavoriteLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when insertLaunch save a launch should contain it from local data source favorite launch list`() = runBlocking {
        // Given
        val expectedObject = getLaunchEntityStub()
        every { localDataSourceMock.insertLaunch(expectedObject) } returns flowOf(Unit)
        every { localDataSourceMock.getFavoriteLaunches() } returns flowOf(listOf(expectedObject))

        // When
        target.insertLaunch(expectedObject)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertTrue(it.contains(expectedObject)) }
    }

    @Test
    fun `when isFavoriteLaunch returns success should emit true value`() = runBlocking {
        // Given
        every { localDataSourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(true)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertTrue(it) }
    }

    @Test
    fun `when isFavoriteLaunch returns fail should emit false value`() = runBlocking {
        // Given
        every { localDataSourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(false)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertFalse(it) }
    }

    @Test
    fun `when delete a launch should not contain it from local data source favorite launch list`() = runBlocking {
        // Given
        val launchEntity = getLaunchEntityStub()
        val expectedList = listOf(getLaunchesEntityStub().last())
        every { localDataSourceMock.insertLaunch(launchEntity) } returns flowOf(Unit)
        every { localDataSourceMock.deleteLaunch(launchEntity) } returns flowOf(Unit)
        every { localDataSourceMock.getFavoriteLaunches() } returns flowOf(expectedList)

        // When
        target.deleteLaunch(launchEntity)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect {
            assertFalse(it.contains(launchEntity))
            assertEquals(expectedList, it)
        }
    }

    @Test
    fun `when getLastYearLaunches returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getLaunchesResponseStub()
        every { remoteDataSourceMock.getLastYearLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when getOnlyLaunchSuccess returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getLaunchesResponseStub()
        every { remoteDataSourceMock.getOnlyLaunchSuccess() } returns flowOf(expectedObject)

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }
}
