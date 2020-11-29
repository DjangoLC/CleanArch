package com.example.data.repository.movie

import com.example.data.repository.region.RegionRepository
import com.example.data.repository.region.RegionsRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Movie

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String,
    private val regionRepository: RegionRepository
) : MovieRepository {

    override suspend fun popularMovies(): List<Movie> {

        if (localDataSource.isEmpty()) {
            val movies =
                remoteDataSource.getPopularMovies(apiKey, regionRepository.findLastRegion())
            localDataSource.saveMovies(movies)
        }

        return remoteDataSource.getPopularMovies(apiKey, regionRepository.findLastRegion())
    }

    override suspend fun findById(id: Int) : Movie {
        return localDataSource.findById(id)
    }

    override suspend fun updateMovie(movie: Movie) {
        return localDataSource.updateMovie(movie)
    }

    override suspend fun favoriteMovies() : List<Movie>{
        return localDataSource.getFavoriteMovies()
    }

}



