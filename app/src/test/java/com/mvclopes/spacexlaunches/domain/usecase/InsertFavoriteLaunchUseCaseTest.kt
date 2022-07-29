package com.mvclopes.spacexlaunches.domain.usecase

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class InsertFavoriteLaunchUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>()
    private val useCase = InsertFavoriteLaunchUseCase(repositoryMock)

    @Test
    fun `when repository returns success useCase should emit Unit`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        coEvery { repositoryMock.insertLaunch(domainLaunch) } returns flowOf(Unit)

        // When
        val result = useCase(domainLaunch)

        // Then
        result.test {
            assertEquals(Unit, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `when repository returns error useCase should emit exception`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { repositoryMock.insertLaunch(domainLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = useCase(domainLaunch)

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
