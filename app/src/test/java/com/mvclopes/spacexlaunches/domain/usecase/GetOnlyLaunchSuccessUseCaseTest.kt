package com.mvclopes.spacexlaunches.domain.usecase

import app.cash.turbine.test
import com.mvclopes.spacexlaunches.domain.repository.SpaceXRepository
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertSame
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetOnlyLaunchSuccessUseCaseTest {

    private val repositoryMock = mockk<SpaceXRepository>()
    private val useCase = GetOnlyLaunchSuccessUseCase(repositoryMock)

    @Test
    fun `when repository returns success useCase should emit a list of domain launch`() = runBlocking {
        // Given
        val domainList = getDomainLaunchListStub()
        coEvery { repositoryMock.getOnlyLaunchSuccess() } returns flowOf(domainList)

        // When
        val result = useCase()

        // Then
        result.test {
            assertEquals(domainList, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `when repository returns error useCase should emit exception`() = runBlocking {
        // Given
        val expectedThrowable = Exception("Generic Error")
        coEvery { repositoryMock.getOnlyLaunchSuccess() } returns flow { throw expectedThrowable }

        // When
        val result = useCase()

        // Then
        result.test { assertSame(expectedThrowable, expectError()) }
    }
}
