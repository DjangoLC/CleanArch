package com.example.usecases

import com.example.data.repository.movie.MovieRepository
import com.example.domain.Movie

class ToggleMovieFavorite(private val moviesRepository: MovieRepository) {

    suspend fun invoke(movie: Movie) = with(movie) {
        copy(favorite = !favorite).also {
            moviesRepository.updateMovie(it)
        }
    }

}