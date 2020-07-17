package com.example.data

import com.example.domain.Movie

interface FilterMovies {

    fun filterBy(movieList: List<Movie>): List<Movie>

}