package com.mvclopes.spacexlaunches.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvclopes.spacexlaunches.domain.usecase.GetAllLaunchesUseCase
import com.mvclopes.spacexlaunches.domain.usecase.GetFavoriteLaunchesUseCase
import com.mvclopes.spacexlaunches.domain.usecase.GetLastYearLaunchesUseCase
import com.mvclopes.spacexlaunches.domain.usecase.GetOnlyLaunchSuccessUseCase
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.utils.MainCoroutineRule
import com.mvclopes.spacexlaunches.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getAllLaunchesUseCase = mockk<GetAllLaunchesUseCase>(relaxed = true)
    private val getOnlyLaunchSuccessUseCase = mockk<GetOnlyLaunchSuccessUseCase>(relaxed = true)
    private val getLastYearLaunchesUseCase = mockk<GetLastYearLaunchesUseCase>(relaxed = true)
    private val getFavoriteLaunchesUseCase = mockk<GetFavoriteLaunchesUseCase>(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()

    private val target = HomeViewModel(
        getAllLaunchesUseCase = getAllLaunchesUseCase,
        getOnlyLaunchSuccessUseCase = getOnlyLaunchSuccessUseCase,
        getLastYearLaunchesUseCase = getLastYearLaunchesUseCase,
        getFavoriteLaunchesUseCase = getFavoriteLaunchesUseCase,
        dispatcher = dispatcher
    )

    @Test
    fun `when getAllLaunches returns success should trigger success state`() {
        mainCoroutineRule.runBlockingTest {}
            // Given
            val expectedResult = getDomainLaunchListStub()
            coEvery { getAllLaunchesUseCase() } returns flowOf(expectedResult)

            // When
            target.getAllLaunches()
            val state = target.state.getOrAwaitValue()

            // Then
            assertEquals(state, HomeUiState.Success(expectedResult))

    }

    @Test
    fun `when getOnlyLaunchSuccess returns success should trigger success state`() {
        mainCoroutineRule.runBlockingTest {
            // Given
            val expectedResult = getDomainLaunchListStub()
            coEvery { getOnlyLaunchSuccessUseCase() } returns flowOf(expectedResult)

            // When
            target.getOnlyLaunchSuccess()
            val state = target.state.getOrAwaitValue()

            // Then
            assertEquals(state, HomeUiState.Success(expectedResult))
        }
    }

    @Test
    fun `when getLastYearLaunches returns success should trigger success state`() {
        mainCoroutineRule.runBlockingTest {
            // Given
            val expectedResult = getDomainLaunchListStub()
            coEvery { getLastYearLaunchesUseCase() } returns flowOf(expectedResult)

            // When
            target.getLastYearLaunches()
            val state = target.state.getOrAwaitValue()

            // Then
            assertEquals(state, HomeUiState.Success(expectedResult))
        }
    }

    @Test
    fun `when getFavoriteLaunches returns success should trigger success state`() {
        mainCoroutineRule.runBlockingTest {
            // Given
            val expectedResult = getDomainLaunchListStub()
            coEvery { getFavoriteLaunchesUseCase() } returns flowOf(expectedResult)

            // When
            target.getFavoriteLaunches()
            val state = target.state.getOrAwaitValue()

            // Then
            assertEquals(state, HomeUiState.Success(expectedResult))
        }
    }
}
