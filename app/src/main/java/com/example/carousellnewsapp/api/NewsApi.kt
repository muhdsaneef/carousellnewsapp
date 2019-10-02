package com.example.carousellnewsapp.api

import com.example.carousellnewsapp.models.Article
import com.example.carousellnewsapp.util.Constants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET(Constants.NEWS_URL)
    fun fetchNewsArticles(): Single<Response<List<Article>>>
}