package com.mvclopes.spacexlaunches.data.repository

import com.mvclopes.spacexlaunches.data.datasource.SpaceXDataSource
import com.mvclopes.spacexlaunches.data.mapper.toDomain
import com.mvclopes.spacexlaunches.data.mapper.toEntity
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchStub
import com.mvclopes.spacexlaunches.stubs.getFlightNumberStub
import com.mvclopes.spacexlaunches.stubs.getResponseLaunchListStub
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpaceXRepositoryImplTest {

    private val datasourceMock = mockk<SpaceXDataSource>()
    private val target = SpaceXRepositoryImpl(datasource = datasourceMock)

    @Test
    fun `when getAllLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { datasourceMock.getAllLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getAllLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when getFavoriteLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getEntityLaunchListStub()
        every { datasourceMock.getFavoriteLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when insertLaunch save a launch should contain it from datasource favorite launch list`() = runBlocking {
        // Given
        val expectedObject = getDomainLaunchStub()
        val entityLaunch = expectedObject.toEntity()
        every { datasourceMock.insertLaunch(entityLaunch) } returns flowOf(Unit)
        every { datasourceMock.getFavoriteLaunches() } returns flowOf(listOf(entityLaunch))

        // When
        target.insertLaunch(expectedObject)
        val result = target.getFavoriteLaunches()

        // Then
        result.collect { assertTrue(it.contains(expectedObject)) }
    }

    @Test
    fun `when isFavoriteLaunch returns success should emit true value`() = runBlocking {
        // Given
        every { datasourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(true)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertTrue(it) }
    }

    @Test
    fun `when isFavoriteLaunch returns fail should emit false value`() = runBlocking {
        // Given
        every { datasourceMock.isFavoriteLaunch(getFlightNumberStub()) } returns flowOf(false)

        // When
        val result = target.isFavoriteLaunch(getFlightNumberStub())

        // Then
        result.collect { assertFalse(it) }
    }

    @Test
    fun `when delete a launch should not contain it from datasource favorite launch list`() = runBlocking {
        // Given
        val launchDomain = getDomainLaunchStub()
        val entityLaunch = getEntityLaunchStub()
        val expectedList = listOf(getDomainLaunchListStub().last())
        val entityList = expectedList.toEntity()
        every { datasourceMock.insertLaunch(entityLaunch) } returns flowOf(Unit)
        every { datasourceMock.deleteLaunch(entityLaunch) } returns flowOf(Unit)
        every { datasourceMock.getFavoriteLaunches() } returns flowOf(entityList)

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
    fun `when getLastYearLaunches returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { datasourceMock.getLastYearLaunches() } returns flowOf(expectedObject)

        // When
        val result = target.getLastYearLaunches()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }

    @Test
    fun `when getOnlyLaunchSuccess returns success should emit a launch domain list`() = runBlocking {
        // Given
        val expectedObject = getResponseLaunchListStub()
        every { datasourceMock.getOnlyLaunchSuccess() } returns flowOf(expectedObject)

        // When
        val result = target.getOnlyLaunchSuccess()

        // Then
        result.collect { assertEquals(expectedObject.toDomain(), it) }
    }
}
