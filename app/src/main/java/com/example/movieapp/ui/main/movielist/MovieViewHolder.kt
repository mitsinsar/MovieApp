package com.example.movieapp.ui.main.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val posterImageView = itemView.itemMoviePosterImageView
    private val titleTextView = itemView.itemMovieTitleTextView
    private val overviewTextView = itemView.itemMovieOverviewTextView

    fun bind(movie: Movie) {
        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        Glide.with(posterImageView).load(movie.imageUrl).into(posterImageView)
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MovieViewHolder(view)
        }
    }
}
