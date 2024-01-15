package com.instantsystem.newswave.composables

import NewsImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.util.Util
import com.instantsystem.newswave.core.navigation.Screen
import com.instantsystem.newswave.data.models.NewsResponse
import com.instantsystem.newswave.data.models.SingleNewsResponse
import com.instantsystem.newswave.utils.Constants.CELL_COUNT
import com.instantsystem.newswave.utils.Constants.NEWS_KEY
import com.instantsystem.newswave.utils.Status


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsGridListComposable(
    navController: NavController,
    listNews: NewsResponse,
    scrollState: LazyGridState
) {
    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Fixed(CELL_COUNT),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
        content = {
            items(listNews.articles!!.size) { index ->
                NewsItem(navController = navController, listNews.articles[index])
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItem(navController: NavController, singleNewsResponse: SingleNewsResponse) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(300.dp)
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp,
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set(NEWS_KEY, singleNewsResponse)
            navController.navigate(Screen.NewsDetailScreen.route)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            singleNewsResponse.urlToImage?.let {
                NewsImage(
                    imagePath = it,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .background(Color.Transparent)
            ) {
                NewsSource(singleNewsResponse.source?.name ?: "")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Transparent)
            ) {
                NewsTitle(singleNewsResponse.title ?: "")
            }
        }

    }
}

@Composable
private fun NewsTitle(name: String) = Text(
    text = name,
    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.Black,
        letterSpacing = 1.5.sp,
    ),
    maxLines = 3,
    overflow = TextOverflow.Ellipsis
)
@Composable
private fun NewsSource(source: String) = Text(
    text = source,
    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.Black,
        letterSpacing = 1.sp,
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)


