package com.example.cleanarchme.views.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchme.R
import com.example.cleanarchme.views.common.PermissionRequester
import com.example.cleanarchme.views.detail.DetailActivity
import com.example.domain.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.lifecycleScope

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    private val presenter: MainContract.MainPresenter by lifecycleScope.inject()

    private val permissionRequester = PermissionRequester(this, ACCESS_COARSE_LOCATION)
    private val moviesAdapter by lazy {
        MoviesAdapter {
            presenter.onMovieClick(it.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()
    }

    private fun setUp() {
        presenter.attach(this)
        recyclerMovies.adapter = moviesAdapter
        spinner.onItemSelectedListener = ManagerSpinnerMovies {
            presenter.setFilterType(it)
            presenter.loadMovies()
        }
        permissionRequester.request {}
        presenter.loadMovies()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun setupMovies(movies: List<Movie>) {
        moviesAdapter.movies = movies
    }

    override fun navigateToDetail(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, id)
        startActivity(intent)
    }
}
