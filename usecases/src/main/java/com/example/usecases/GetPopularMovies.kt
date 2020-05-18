package com.example.usecases

import com.example.data.repository.movie.MovieRepository

class GetPopularMovies(private val moviesRepository: MovieRepository) {

    suspend fun invoke() = moviesRepository.popularMovies()

}