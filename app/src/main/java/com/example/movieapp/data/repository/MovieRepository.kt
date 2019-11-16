package com.example.movieapp.data.repository

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieDetail
import com.example.movieapp.data.network.MovieApi

class MovieRepository(private val movieApi: MovieApi): BaseRepository() {

    suspend fun getPopularMovies(): List<Movie> {
        return movieApi.getPopularMovies().results
    }

    suspend fun getMovieDetail(movieId: String): MovieDetail {
        return movieApi.getMovieDetail(movieId)
    }
}
