package com.example.cleanarchme.views

interface BasePresenter<V : BaseView> {
    fun attach(view: V)
    fun detach()
}