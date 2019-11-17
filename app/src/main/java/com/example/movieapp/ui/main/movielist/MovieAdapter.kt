package com.example.movieapp.ui.main.movielist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.model.Movie

class MovieAdapter(private val onItemClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieViewHolder>() {

    private val items = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent).apply {
            itemView.setOnClickListener { onItemClick(items[adapterPosition]) }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<Movie>) {
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
}
