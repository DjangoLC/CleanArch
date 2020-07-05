package com.example.cleanarchme.views.main

import android.view.View
import android.widget.AdapterView
import com.example.cleanarchme.R
import com.example.data.MovieFilterType

class ManagerSpinnerMovies(private val movieListener: (MovieFilterType) -> Unit) :
    AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.adapter?.getItem(position) as String) {
            parent.context.getString(R.string.all) -> {
                movieListener(MovieFilterType.ALL_MOVIES)
            }

            parent.context.getString(R.string.favorite) -> {
                movieListener(MovieFilterType.FAVORITES_MOVIES)
            }
        }
    }
}