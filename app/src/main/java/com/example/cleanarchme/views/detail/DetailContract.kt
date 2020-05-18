package com.example.cleanarchme.views.detail

import com.example.cleanarchme.views.BasePresenter
import com.example.cleanarchme.views.BaseView
import com.example.domain.Movie

interface DetailContract {

    interface DetailView : BaseView{
        fun setMovie(movie: Movie)
        fun setFavorite(boolean: Boolean)
    }

    interface DetailPresenter : BasePresenter<DetailView>{
        fun onLoadInfo()
        fun onFavoriteMovieClick()
    }
}