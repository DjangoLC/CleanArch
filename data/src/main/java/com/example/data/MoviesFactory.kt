package com.example.data

class MoviesFactory {

    companion object {
        fun create(filter: MovieFilterType): FilterMovies {
            return when (filter) {
                MovieFilterType.ALL_MOVIES -> FilterAllMovies()
                MovieFilterType.FAVORITES_MOVIES -> FilterFavoriteMovies()
            }
        }
    }

}