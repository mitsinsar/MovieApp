package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.model.MovieDetail
import com.example.movieapp.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("popular/")
    suspend fun getPopularMovies(
        @Query(QUERY_API_KEY) apiKey: String? = BuildConfig.API_KEY,
        @Query(QUERY_LANGUAGE) language: String? = "en-US"
    ): PopularMoviesResponse

    @GET("{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: String?,
        @Query(QUERY_API_KEY) apiKey: String? = BuildConfig.API_KEY,
        @Query(QUERY_LANGUAGE) language: String? = "en-US"
    ): MovieDetail

    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_LANGUAGE = "language"
    }
}
