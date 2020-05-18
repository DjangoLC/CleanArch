package com.example.cleanarchme

import android.app.Application
import com.example.cleanarchme.di.initDI

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}