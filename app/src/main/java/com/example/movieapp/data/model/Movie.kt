package com.example.movieapp.data.model

import com.example.movieapp.BuildConfig
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?
) {
    fun getImageUrl(): String {
        return "${BuildConfig.BASE_IMAGE_URL}$posterPath"
    }
}
