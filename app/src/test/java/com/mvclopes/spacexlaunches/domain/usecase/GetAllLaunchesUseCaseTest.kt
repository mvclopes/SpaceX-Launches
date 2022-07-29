package com.mvclopes.spacexlaunches.domain.usecase

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetAllLaunchesUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>()
    private val useCase = GetAllLaunchesUseCase(repositoryMock)

    @Test
    fun `when repository returns success useCase should emit a list of domain launch`() = runBlocking {
        // Given
        val domainList = getDomainLaunchListStub()
        coEvery { repositoryMock.getAllLaunches() } returns flowOf(domainList)

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
        coEvery { repositoryMock.getAllLaunches() } returns flow { throw expectedThrowable }

        // When
        val result = useCase()

        // Then
        result.test { assertSame(expectedThrowable, awaitError()) }
    }
}
