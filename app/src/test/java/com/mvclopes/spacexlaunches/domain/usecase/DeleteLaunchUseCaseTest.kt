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
class DeleteLaunchUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>(relaxed = true)
    private val useCase = DeleteLaunchUseCase(repositoryMock)

    @Test
    fun `when repository returns success useCase should emit Unit`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        coEvery { repositoryMock.deleteLaunch(domainLaunch) } returns flowOf(Unit)

        // When
        val result = useCase(domainLaunch)

        result.test {
            assertEquals(Unit, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `when repository returns error useCase should emit exception`() = runBlocking {
        // Given
        val domainLaunch = getDomainLaunchStub()
        val expectedThrowable = Exception("Generic Error")
        coEvery { repositoryMock.deleteLaunch(domainLaunch) } returns flow { throw expectedThrowable }

        // When
        val result = useCase(domainLaunch)

        result.test { assertSame(expectedThrowable, expectError()) }
    }
}
