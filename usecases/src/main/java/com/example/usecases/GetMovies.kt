package com.example.usecases

import com.example.data.MovieFilterType
import com.example.data.MoviesFactory
import com.example.data.repository.movie.MovieRepository
import com.example.domain.Movie

class GetMovies(private val moviesRepo: MovieRepository) {

    suspend operator fun invoke(filter: MovieFilterType): List<Movie> {
        val movies = moviesRepo.popularMovies()
        val manager = MoviesFactory.create(filter)
        return manager.filterBy(movies)
    }

}