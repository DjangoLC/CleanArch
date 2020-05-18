package com.example.data.repository.region

import com.example.data.Permission
import com.example.data.PermissionChecker
import com.example.data.source.LocationDataSource

class RegionsRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker) : RegionRepository {

    companion object{
       const val DEFAULT_LOCATION = "US"
    }

    override suspend fun findLastRegion(): String {
         return if (permissionChecker.check(Permission.COARSE_LOCATION)){
             locationDataSource.getLastLocation() ?: DEFAULT_LOCATION
         } else {
             DEFAULT_LOCATION
         }
    }

}