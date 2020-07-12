package com.example.data.repository.movie.filter

import com.example.domain.Movie

class FavoriteMovieFilter : MovieFilter {

    override fun filter(movies: List<Movie>): List<Movie> {
        return movies.filter { it.favorite }
    }
}