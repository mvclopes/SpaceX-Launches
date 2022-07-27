package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getResponseLaunchListStub
import org.junit.Assert.*

import org.junit.Test

class LaunchResponseToDomainKtTest {

    @Test
    fun `toDomain should return list from response launch to domain one`() {
        // Given
        val responseList = getResponseLaunchListStub()

        // When
        val domainList = responseList.toDomain()

        // Then
        assertNotSame(responseList, domainList)
    }

    @Test
    fun `toDomain should mapping from launch response to domain one`() {
        // Given
        val responseList = getResponseLaunchListStub()
        val domainList = getDomainLaunchListStub()

        // When
        val result = responseList.toDomain()

        // Then
        assertEquals(domainList, result)
    }
}
