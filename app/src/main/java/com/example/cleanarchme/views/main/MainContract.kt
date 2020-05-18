package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.BasePresenter
import com.example.cleanarchme.views.BaseView
import com.example.domain.Movie

interface MainContract {

    interface MainView : BaseView{
        fun setupMovies(movies: List<Movie>)
        fun navigateToDetail(id: Int)
    }

    interface MainPresenter : BasePresenter<MainView> {
        fun onLoadMovies()
        fun onMovieClick(id: Int)
        fun onLoadFavoritesMovies()
        fun loadMovies(filterType: ManagerSpinnerMovies.MovieFilterType)
    }
}