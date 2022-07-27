package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.stubs.getLaunchesDomainStub
import com.mvclopes.spacexlaunches.stubs.getLaunchesResponseStub
import org.junit.Assert.*

import org.junit.Test

class LaunchResponseToDomainKtTest {

    @Test
    fun `toDomain should return list from response launch to domain one`() {
        // Given
        val responseList = getLaunchesResponseStub()

        // When
        val domainList = responseList.toDomain()

        // Then
        assertNotSame(responseList, domainList)
    }

    @Test
    fun `toDomain should mapping from launch response to domain one`() {
        // Given
        val responseList = getLaunchesResponseStub()
        val domainList = getLaunchesDomainStub()

        // When
        val result = responseList.toDomain()

        // Then
        assertEquals(domainList, result)
    }
}
