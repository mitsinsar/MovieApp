package com.example.movieapp.data.network

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("popular.json")
    suspend fun getPopularMovies(): List<Movie>?

    @GET("details/{id}.json")
    suspend fun getMovieDetail(
        @Path("id") movieId: String?
    ): MovieDetail?
}
