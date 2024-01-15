package com.instantsystem.newswave.data.datasources

import com.instantsystem.newswave.data.models.NewsResponse
import com.instantsystem.newswave.data.models.SingleNewsResponse
import com.instantsystem.newswave.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET(Constants.BASE_URL+"top-headlines")
    suspend fun getNews(@Query("language") language: String): NewsResponse
}