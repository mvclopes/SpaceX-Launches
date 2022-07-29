package com.mvclopes.spacexlaunches.domain.usecase

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getFlightNumberStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class IsFavoriteLaunchUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>()
    private val useCase = IsFavoriteLaunchUseCase(repositoryMock)

    @Test
    fun `when launch is favorite one useCase should emit true`() = runBlocking {
        // Given
        val flightNumber = getFlightNumberStub()
        coEvery { repositoryMock.isFavoriteLaunch(flightNumber) } returns flowOf(true)

        // When
        val result = useCase(flightNumber)

        // Then
        result.test {
            assertTrue(awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `when launch is not favorite one useCase should emit false`() = runBlocking {
        // Given
        val flightNumber = getFlightNumberStub()
        coEvery { repositoryMock.isFavoriteLaunch(flightNumber) } returns flowOf(false)

        // When
        val result = useCase(flightNumber)

        // Then
        result.test {
            assertFalse(awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `when repository returns error useCase should emit exception`() = runBlocking {
        // Given
        val flightNumber = getFlightNumberStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { repositoryMock.isFavoriteLaunch(flightNumber) } returns flow { throw expectedThrowable }

        // When
        val result = useCase(flightNumber)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
