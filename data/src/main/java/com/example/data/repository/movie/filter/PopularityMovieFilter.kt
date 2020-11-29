package com.example.data.repository.movie.filter

import com.example.domain.Movie

class PopularityMovieFilter : MovieFilter {

    override fun filter(movies: List<Movie>): List<Movie> {
        return movies.sortedByDescending { it.popularity }
    }
}