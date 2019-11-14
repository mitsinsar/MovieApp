package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        private var movieApi: MovieApi? = null

        @JvmStatic
        fun getMovieApi(): MovieApi {
            return if (movieApi == null) {
                movieApi = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieApi::class.java)
                movieApi!!
            } else {
                movieApi!!
            }
        }
    }
}
