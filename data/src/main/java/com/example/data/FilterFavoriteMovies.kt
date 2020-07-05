package com.example.data

import com.example.domain.Movie

class FilterFavoriteMovies : FilterMovies {

    override fun filterBy(movieList: List<Movie>): List<Movie> {
        return movieList.filter {
            it.favorite
        }
    }
}