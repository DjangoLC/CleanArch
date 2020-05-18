package com.example.data.repository.region

interface RegionRepository {
    suspend fun findLastRegion(): String
}