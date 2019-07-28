package com.example.carousellnewsapp.models

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("banner_url") val bannerUrl: String,
    val description: String,
    val id: String,
    val rank: Int,
    @SerializedName("time_created") val timeCreated: Long,
    val title: String
)