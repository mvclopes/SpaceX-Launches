package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.stubs.getLaunchDomainStub
import com.mvclopes.spacexlaunches.stubs.getLaunchEntityStub
import com.mvclopes.spacexlaunches.stubs.getLaunchesDomainStub
import com.mvclopes.spacexlaunches.stubs.getLaunchesEntityStub
import org.junit.Assert.*

import org.junit.Test

class LaunchEntityToDomainKtTest {

    @Test
    fun `toDomain should return list from entity launch to domain one`() {
        // Given
        val entityList = getLaunchesEntityStub()
        val expectedDomainList = getLaunchesDomainStub()

        // When
        val domainList = entityList.toDomain()

        // Then
        assertNotSame(entityList, domainList)
        assertEquals(expectedDomainList, domainList)
    }

    @Test
    fun `toDomain should return an object from entity launch to domain one`() {
        // Given
        val entityLaunch = getLaunchEntityStub()
        val expectedDomainObject = getLaunchDomainStub()

        // When
        val domainLaunch = entityLaunch.toDomain()

        // Then
        assertNotSame(entityLaunch, domainLaunch)
        assertEquals(expectedDomainObject, domainLaunch)
    }

    @Test
    fun `toEntity should return list from domain launch to entity one`() {
        // Given
        val domainList = getLaunchesDomainStub()
        val expectedEntityList = getLaunchesEntityStub()

        // When
        val entityList = domainList.toEntity()

        // Then
        assertNotSame(domainList, entityList)
        assertEquals(expectedEntityList, entityList)
    }

    @Test
    fun `toEntity should return an object from domain launch to entity one`() {
        // Given
        val domainLaunch = getLaunchDomainStub()
        val expectedEntityObject = getLaunchEntityStub()

        // When
        val entityLaunch = domainLaunch.toEntity()

        // Then
        assertNotSame(domainLaunch, entityLaunch)
        assertEquals(expectedEntityObject, entityLaunch)
    }
}
