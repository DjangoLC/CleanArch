package com.example.data.repository.movie.filter

import com.example.domain.Movie

interface MovieFilter {
    fun filter(movies: List<Movie>) : List<Movie>
}