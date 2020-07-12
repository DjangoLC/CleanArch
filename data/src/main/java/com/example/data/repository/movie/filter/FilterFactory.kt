package com.example.data.repository.movie.filter

class FilterFactory {

    fun create(filterType: MovieFilterType): MovieFilter {
        return when (filterType) {
            MovieFilterType.ALL -> AllMoviesFilter()
            MovieFilterType.FAVORITES -> FavoriteMovieFilter()
            MovieFilterType.POPULARITY -> PopularityMovieFilter()
        }
    }

}