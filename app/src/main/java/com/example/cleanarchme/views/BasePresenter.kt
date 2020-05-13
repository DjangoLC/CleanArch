package com.example.cleanarchme.views

interface BasePresenter<T> {

    fun attach(view: T)

    fun detach()

}