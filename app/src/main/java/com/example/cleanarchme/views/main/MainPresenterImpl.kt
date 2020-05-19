package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.BasePresenterImpl
import com.example.cleanarchme.views.common.Scope
import com.example.usecases.GetFavoritesMovies
import com.example.usecases.GetPopularMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainPresenterImpl(
    private val getPopularMovies: GetPopularMovies,
    private val getFavoritesMovies: GetFavoritesMovies,
    uiDispatcher: CoroutineDispatcher
) : BasePresenterImpl<MainContract.MainView>(uiDispatcher), MainContract.MainPresenter {

    override fun onLoadMovies() {
        launch {
            val movies = getPopularMovies.invoke()
            view.setupMovies(movies)
        }
    }

    override fun onMovieClick(id: Int) {
        view.navigateToDetail(id)
    }

    override fun onLoadFavoritesMovies() {
        launch {
            val movies = getFavoritesMovies.invoke()
            view.setupMovies(movies)
        }
    }

    override fun loadMovies(filterType: ManagerSpinnerMovies.MovieFilterType) {
        when (filterType) {
            ManagerSpinnerMovies.MovieFilterType.ALL_MOVIES -> onLoadMovies()
            ManagerSpinnerMovies.MovieFilterType.FAVORITES_MOVIES -> onLoadFavoritesMovies()
        }
    }
}