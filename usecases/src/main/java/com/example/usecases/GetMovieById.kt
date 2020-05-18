package com.example.usecases

import com.example.data.repository.movie.MovieRepository
import com.example.domain.Movie

class GetMovieById(private val moviesRepository: MovieRepository) {

    suspend fun invoke(id: Int): Movie {
        return moviesRepository.findById(id)
    }
}