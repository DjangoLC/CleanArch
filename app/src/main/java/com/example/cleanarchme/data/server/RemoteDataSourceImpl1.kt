package com.example.cleanarchme.data.server

import com.example.cleanarchme.views.common.toDomainMovie
import com.example.data.source.RemoteDataSource
import com.example.domain.Movie

class RemoteDataSourceImpl1(private val movieDbService: MovieDbService) : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String, findLastRegion: String): List<Movie> {
        return try {
            movieDbService.listPopularMoviesAsync(
                apiKey,
                findLastRegion
            ).results.map { it.toDomainMovie() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emptyList()
        }
    }
}