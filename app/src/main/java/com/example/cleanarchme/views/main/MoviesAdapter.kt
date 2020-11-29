package com.example.cleanarchme.views.main


import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchme.R
import com.example.cleanarchme.views.common.AspectRatioImageView
import com.example.cleanarchme.views.common.basicDiffUtil
import com.example.cleanarchme.views.common.inflate
import com.example.cleanarchme.views.common.loadUrl
import com.example.domain.Movie
import kotlinx.android.synthetic.main.row_movie.view.*
import kotlin.properties.Delegates

const val POSITION = 1

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var showShimmer = true

    var movies: List<Movie> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.row_movie, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = if (showShimmer) 10 else movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if (showShimmer) {
            holder.itemView.shimmer.startShimmer()
        } else {

            holder.itemView.shimmer.stopShimmer()
            holder.itemView.shimmer.setShimmer(null)

            val movie = movies[position]
            holder.bind(movie)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = itemView.movieTitle
        private val image: AspectRatioImageView = itemView.movieCover

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            image.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }

    }
}