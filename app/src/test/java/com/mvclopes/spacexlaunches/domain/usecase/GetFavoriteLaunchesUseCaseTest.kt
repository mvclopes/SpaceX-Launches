package com.mvclopes.spacexlaunches.domain.usecase

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
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
class GetFavoriteLaunchesUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>(relaxed = true)
    private val useCase = GetFavoriteLaunchesUseCase(repositoryMock)

    @Test
    fun `when repository returns success useCase should emit a list of domain launch`() = runBlocking {
        // Given
        val domainList = getDomainLaunchListStub()
        coEvery { repositoryMock.getFavoriteLaunches() } returns flowOf(domainList)

        // When
        val result = useCase()

        // Then
        result.test {
            assertEquals(domainList, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `when repository returns error useCase should emit exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { repositoryMock.getFavoriteLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = useCase()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
