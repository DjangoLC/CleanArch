package com.example.cleanarchme.views.detail

import com.example.cleanarchme.views.BasePresenterImpl
import com.example.usecases.GetMovieById
import com.example.usecases.ToggleMovieFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailPresenterImpl(
    private val getMovieById: GetMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite,
    private val movieId: Int,
    uiDispatcher: CoroutineDispatcher
) : BasePresenterImpl<DetailContract.DetailView>(uiDispatcher), DetailContract.DetailPresenter{

    override fun onLoadInfo() {
        launch {
            val movie = getMovieById.invoke(movieId)
            view?.setMovie(movie)
            view?.setFavorite(movie.favorite)
        }
    }

    override fun onFavoriteMovieClick() {
        launch {
            val movie = getMovieById.invoke(movieId)
            val movieUpdate = toggleMovieFavorite.invoke(movie)
            view?.setFavorite(movieUpdate.favorite)
        }
    }
}