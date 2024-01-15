package com.instantsystem.newswave.features.news.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instantsystem.newswave.data.models.NewsResponse
import com.instantsystem.newswave.data.models.SingleNewsResponse
import com.instantsystem.newswave.data.repositories.NewsRepository
import com.instantsystem.newswave.utils.Status
import com.instantsystem.newswave.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

     var _newsState = MutableStateFlow(
        ViewState(
            Status.LOADING,
            NewsResponse(), ""
        )
    )
     var listAllNews = NewsResponse()

    init {
        fetchNews()
    }
    private fun fetchNews() {

        Log.i("NewsViewModel", "fetchNews")

        _newsState.value = ViewState.loading()
        viewModelScope.launch {
            val deviceLanguage = Locale.getDefault().language

            newsRepository.fetchNewsData(deviceLanguage)
                .catch {
                    _newsState.value =
                        ViewState.error(it.message.toString())
                    Log.i("NewsViewModel", "${it.message.toString()})")
                }
                .collect { response ->
                    _newsState.value = ViewState.success(response.data)
                    listAllNews = response.data!!
                }
        }
    }
}