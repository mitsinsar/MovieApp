package com.example.movieapp.data.model

import com.example.movieapp.BuildConfig
import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?
) {
    fun getPosterUrl(): String {
        return "${BuildConfig.BASE_IMAGE_URL}$posterPath"
    }

    fun getBackdropUrl(): String {
        return "${BuildConfig.BASE_IMAGE_URL}$backdropPath"
    }
}
