package com.example.data.repository.movie

import com.example.domain.Movie

interface MovieRepository {
    suspend fun popularMovies(): List<Movie>
    suspend fun findById(id: Int) : Movie
    suspend fun updateMovie(movie: Movie)
    suspend fun favoriteMovies() : List<Movie>
}