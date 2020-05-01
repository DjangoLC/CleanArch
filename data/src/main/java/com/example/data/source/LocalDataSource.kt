package com.example.data.source

import com.example.domain.Movie

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun getPopularMovies(): List<Movie>
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun findById(id: Int): Movie
    suspend fun updateMovie(movie: Movie)
    suspend fun getFavoriteMovies(): List<Movie>
}