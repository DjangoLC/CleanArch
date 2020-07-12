package com.example.usecases

import com.example.data.repository.movie.MovieRepository
import com.example.data.repository.movie.filter.FilterFactory
import com.example.data.repository.movie.filter.MovieFilterType
import com.example.domain.Movie

class GetMovies(
    private val repository: MovieRepository,
    private val filterFactory: FilterFactory) {

    suspend fun invoke(filterType: MovieFilterType): List<Movie> {

        val movies = repository.getMovies()
        val movieFilter = filterFactory.create(filterType)
        return movieFilter.filter(movies)
    }

}