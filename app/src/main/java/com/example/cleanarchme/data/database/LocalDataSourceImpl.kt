package com.example.cleanarchme.data.database

import com.example.cleanarchme.views.common.toDomain
import com.example.cleanarchme.views.common.toMovieDb
import com.example.data.source.LocalDataSource
import com.example.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { movieDao.movieCount() == 0 }


    override suspend fun getPopularMovies(): List<Movie> =
        withContext(Dispatchers.IO) { movieDao.getAll().map { it.toDomain() } }

    override suspend fun saveMovies(movies: List<Movie>) =
        withContext(Dispatchers.IO) { movieDao.insertMovies(movies.map { it.toMovieDb() }) }


    override suspend fun findById(id: Int): Movie =
        withContext(Dispatchers.IO) { movieDao.findById(id).toDomain() }

    override suspend fun updateMovie(movie: Movie) =
        withContext(Dispatchers.IO) { movieDao.updateMovie(movie.toMovieDb()) }


}