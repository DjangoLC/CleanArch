package com.example.usecases

import com.example.data.repository.movie.MovieRepository
import com.example.domain.Movie

class GetFavoritesMovies(private val moviesRepository: MovieRepository) {

    suspend fun invoke() : List<Movie> {
        return moviesRepository.favoriteMovies()
    }

}