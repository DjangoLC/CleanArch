package com.example.data

import com.example.domain.Movie

class FilterAllMovies() : FilterMovies {

    override fun filterBy(movieList: List<Movie>): List<Movie> {
        return movieList
    }
}