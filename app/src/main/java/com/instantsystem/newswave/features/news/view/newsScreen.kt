package com.instantsystem.newswave.features.news.view


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.instantsystem.newswave.R
import com.instantsystem.newswave.composables.NewsGridListComposable
import com.instantsystem.newswave.features.news.viewModel.NewsViewModel
import com.instantsystem.newswave.utils.Status


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun NewsScreen(navController: NavController) {
    val scrollState = rememberLazyGridState()
    val newsViewModel: NewsViewModel = hiltViewModel()
    val newsState = newsViewModel._newsState.collectAsState()
    val newsList = newsViewModel.listAllNews

    when (newsState.value.status) {
        Status.SUCCESS -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.news_screen_title)) },
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    ) {
                        NewsGridListComposable(
                            navController = navController,
                            listNews = newsList,
                            scrollState = scrollState,
                        )
                    }

                }

            )
        }
        Status.LOADING -> {
            // Add loading message
        }

        else -> {
            // Add error message
        }
    }
}
