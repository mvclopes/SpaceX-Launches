package com.mvclopes.spacexlaunches.data.repository

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.mapper.toDomain
import com.mvclopes.spacexlaunches.data.mapper.toEntity
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchStub
import com.mvclopes.spacexlaunches.stubs.getFlightNumberStub
import com.mvclopes.spacexlaunches.stubs.getResponseLaunchListStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class SpaceXRepositoryImplTest {

    private val datasourceMock = mockk<SpaceXDataSource>(relaxed = true)
    private val target = SpaceXRepositoryImpl(datasource = datasourceMock)

    @Test
    fun `when getAllLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        coEvery { datasourceMock.getAllLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getAllLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when dataSource returns error getAllLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.getAllLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getAllLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getFavoriteLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getEntityLaunchListStub()
        coEvery { datasourceMock.getFavoriteLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when dataSource returns error getFavoriteLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.getFavoriteLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when insertLaunch save a launch should contain it from datasource favorite launch list`() = runBlocking {
        // Given
        val expectedObject = getDomainLaunchStub()
        val entityLaunch = expectedObject.toEntity()
        coEvery { datasourceMock.insertLaunch(entityLaunch) } returns flowOf(Unit)
        coEvery { datasourceMock.getFavoriteLaunches() } returns flowOf(listOf(entityLaunch))

        // When
        target.insertLaunch(expectedObject)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertTrue(it.contains(expectedObject)) }
    }

    @Test
    fun `when dataSource returns error insertLaunch should emit an exception`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        val entityLaunch = domainLaunch.toEntity()
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.insertLaunch(entityLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = target.insertLaunch(domainLaunch)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when isFavoriteLaunch returns success should emit true value`() = runBlocking {
        // Given
        coEvery { datasourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(true)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertTrue(it) }
    }

    @Test
    fun `when isFavoriteLaunch returns fail should emit false value`() = runBlocking {
        // Given
        coEvery { datasourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(false)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertFalse(it) }
    }

    @Test
    fun `when dataSource returns error isFavoriteLaunch should emit an exception`() = runBlocking {
        // Given
        val flightNumber = getFlightNumberStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.isFavoriteLaunch(flightNumber) } returns flow { throw expectedThrowable }

        // When
        val result = target.isFavoriteLaunch(flightNumber)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when delete a launch should not contain it from datasource favorite launch list`() = runBlocking {
        // Given
        val launchDomain = getDomainLaunchStub()
        val entityLaunch = getEntityLaunchStub()
        val expectedList = listOf(getDomainLaunchListStub().last())
        val entityList = expectedList.toEntity()
        coEvery { datasourceMock.insertLaunch(entityLaunch) } returns flowOf(Unit)
        coEvery { datasourceMock.deleteLaunch(entityLaunch) } returns flowOf(Unit)
        coEvery { datasourceMock.getFavoriteLaunches() } returns flowOf(entityList)

        // When
        target.deleteLaunch(launchDomain)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect {
            assertFalse(it.contains(launchDomain))
            assertEquals(expectedList, it)
        }
    }

    @Test
    fun `when dataSource returns error deleteLaunch should emit an exception`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        val entityLaunch = domainLaunch.toEntity()
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.deleteLaunch(entityLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = target.deleteLaunch(domainLaunch)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getLastYearLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        coEvery { datasourceMock.getLastYearLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when dataSource returns error getLastYearLaunches should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.getLastYearLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }

    @Test
    fun `when getOnlyLaunchSuccess returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        coEvery { datasourceMock.getOnlyLaunchSuccess() } returns flowOf(expectedObject)

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when dataSource returns error getOnlyLaunchSuccess should emit an exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { datasourceMock.getOnlyLaunchSuccess() } returns flow { throw expectedThrowable }

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
