package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("releaseDate") val releaseDate: String?,
    @SerializedName("backdropUrl") val backdropPath: String?,
    @SerializedName("posterUrl") val posterPath: String?
)
