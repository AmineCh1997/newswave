package com.instantsystem.newswave.data.models

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults : String? = null,
    @SerializedName("articles")
    val articles:  ArrayList<SingleNewsResponse>? = null,
)