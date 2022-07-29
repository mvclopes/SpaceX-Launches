package com.mvclopes.spacexlaunches.data.datasource

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.data.datasource.local.LocalDataSource
import com.mvclopes.spacexlaunches.data.datasource.remote.RemoteDataSource
import com.mvclopes.spacexlaunches.data.mapper.toEntity
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchStub
import com.mvclopes.spacexlaunches.stubs.getFlightNumberStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getResponseLaunchListStub
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class SpaceXDataSourceImplTest {

    private val remoteDataSourceMock = mockk<RemoteDataSource>(relaxed = true)
    private val localDataSourceMock = mockk<LocalDataSource>()
    private val target = SpaceXDataSourceImpl(remoteDataSourceMock, localDataSourceMock)

    @Test
    fun `when getAllLaunches returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { remoteDataSourceMock.getAllLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getAllLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when remoteDataSource returns error getAllLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { remoteDataSourceMock.getAllLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getAllLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getFavoriteLaunches returns success should emit a launch entity list`() = runBlocking {
        // Given
        val expectedObject = getEntityLaunchListStub()
        every { localDataSourceMock.getFavoriteLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when localDataSource returns error getFavoriteLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { localDataSourceMock.getFavoriteLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when insertLaunch save a launch should contain it from local data source favorite launch list`() = runBlocking {
        // Given
        val expectedObject = getEntityLaunchStub()
        every { localDataSourceMock.insertLaunch(expectedObject) } returns flowOf(Unit)
        every { localDataSourceMock.getFavoriteLaunches() } returns flowOf(listOf(expectedObject))

        // When
        target.insertLaunch(expectedObject)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertTrue(it.contains(expectedObject)) }
    }

    @Test
    fun `when localDataSource returns error insertLaunch should emit an exception`() = runBlocking {
        // Given
        val entityLaunch = getEntityLaunchStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { localDataSourceMock.insertLaunch(entityLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = target.insertLaunch(entityLaunch)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
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
    fun `when localDataSource returns error isFavoriteLaunch should emit an exception`() = runBlocking {
        // Given
        val flightNumber = getFlightNumberStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { localDataSourceMock.isFavoriteLaunch(flightNumber) } returns flow { throw expectedThrowable }

        // When
        val result = target.isFavoriteLaunch(flightNumber)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when delete a launch should not contain it from local data source favorite launch list`() = runBlocking {
        // Given
        val launchEntity = getEntityLaunchStub()
        val expectedList = listOf(getEntityLaunchListStub().last())
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
    fun `when localDataSource returns error deleteLaunch should emit an exception`() = runBlocking {
        // Given
        val entityLaunch = getEntityLaunchStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { localDataSourceMock.deleteLaunch(entityLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = target.deleteLaunch(entityLaunch)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getLastYearLaunches returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { remoteDataSourceMock.getLastYearLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when remoteDataSource returns error getLastYearLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { remoteDataSourceMock.getLastYearLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getOnlyLaunchSuccess returns success should emit a launch response list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { remoteDataSourceMock.getOnlyLaunchSuccess() } returns flowOf(expectedObject)

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.collect { assertEquals(expectedObject, it) }
    }

    @Test
    fun `when remoteDataSource returns error getOnlyLaunchSuccess should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { remoteDataSourceMock.getOnlyLaunchSuccess() } returns flow { throw expectedThrowable }

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
