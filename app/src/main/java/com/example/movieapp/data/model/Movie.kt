package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?
)
