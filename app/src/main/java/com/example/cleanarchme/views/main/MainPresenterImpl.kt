package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.BasePresenterImpl
import com.example.data.repository.movie.filter.MovieFilterType
import com.example.usecases.GetMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainPresenterImpl(
    private val getMovies: GetMovies,
    uiDispatcher: CoroutineDispatcher
) : BasePresenterImpl<MainContract.MainView>(uiDispatcher), MainContract.MainPresenter {

    override fun onMovieClick(id: Int) {
        view.navigateToDetail(id)
    }

    override fun loadMovies(filterType: MovieFilterType) {
        launch {
            val movies = getMovies.invoke(filterType)
            view.setupMovies(movies)
        }
    }
}