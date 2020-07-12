package com.example.data.repository.movie.filter

import com.example.domain.Movie

class AllMoviesFilter : MovieFilter {

    override fun filter(movies: List<Movie>): List<Movie> {
        return movies
    }
}