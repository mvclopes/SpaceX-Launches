package com.mvclopes.spacexlaunches.data.mapper

import com.mvclopes.spacexlaunches.stubs.getDomainLaunchStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchStub
import com.mvclopes.spacexlaunches.stubs.getDomainLaunchListStub
import com.mvclopes.spacexlaunches.stubs.getEntityLaunchListStub
import org.junit.Assert.*

import org.junit.Test

class LaunchEntityToDomainKtTest {

    @Test
    fun `toDomain should return list from entity launch to domain one`() {
        // Given
        val entityList = getEntityLaunchListStub()
        val expectedDomainList = getDomainLaunchListStub()

        // When
        val domainList = entityList.toDomain()

        // Then
        assertNotSame(entityList, domainList)
        assertEquals(expectedDomainList, domainList)
    }

    @Test
    fun `toDomain should return an object from entity launch to domain one`() {
        // Given
        val entityLaunch = getEntityLaunchStub()
        val expectedDomainObject = getDomainLaunchStub()

        // When
        val domainLaunch = entityLaunch.toDomain()

        // Then
        assertNotSame(entityLaunch, domainLaunch)
        assertEquals(expectedDomainObject, domainLaunch)
    }

    @Test
    fun `toEntity should return list from domain launch to entity one`() {
        // Given
        val domainList = getDomainLaunchListStub()
        val expectedEntityList = getEntityLaunchListStub()

        // When
        val entityList = domainList.toEntity()

        // Then
        assertNotSame(domainList, entityList)
        assertEquals(expectedEntityList, entityList)
    }

    @Test
    fun `toEntity should return an object from domain launch to entity one`() {
        // Given
        val domainLaunch = getDomainLaunchStub()
        val expectedEntityObject = getEntityLaunchStub()

        // When
        val entityLaunch = domainLaunch.toEntity()

        // Then
        assertNotSame(domainLaunch, entityLaunch)
        assertEquals(expectedEntityObject, entityLaunch)
    }
}
