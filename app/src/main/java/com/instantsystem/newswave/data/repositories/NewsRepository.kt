package com.instantsystem.newswave.data.repositories

import com.instantsystem.newswave.data.datasources.NewsService
import com.instantsystem.newswave.data.models.NewsResponse
import com.instantsystem.newswave.data.models.SingleNewsResponse
import com.instantsystem.newswave.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NewsRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun fetchNewsData(language: String): Flow<ViewState<NewsResponse>> {
        return flow {
            val comment = newsService.getNews(language)
            emit(ViewState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}
