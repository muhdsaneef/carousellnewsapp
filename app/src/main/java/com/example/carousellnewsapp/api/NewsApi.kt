package com.example.carousellnewsapp.api

import com.example.carousellnewsapp.models.Article
import com.example.carousellnewsapp.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET(Constants.NEWS_URL)
    fun fetchNewsArticles(): Call<List<Article>>
}