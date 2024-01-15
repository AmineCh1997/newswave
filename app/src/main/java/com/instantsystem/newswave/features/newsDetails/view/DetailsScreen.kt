package com.instantsystem.newswave.features.newsDetails.view

import HyperlinkText
import NewsImage
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.instantsystem.newswave.data.models.SingleNewsResponse


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(singleNewsResponse: SingleNewsResponse) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)) {
            Card {
                Column {
                    NewsImage(
                        imagePath = singleNewsResponse.urlToImage ?: "",
                        Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                    )
                    singleNewsResponse.publishedAt?.let { publishedAt ->
                        Text(
                            text = publishedAt,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                    singleNewsResponse.source?.name?.let { sourceName ->
                        Text(
                            text = sourceName,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                    singleNewsResponse.description?.let { description ->
                        Text(
                            text = description,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                    singleNewsResponse.url?.let { url ->
                        HyperlinkText(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            fullText = url,
                            linkText = listOf(url),
                            hyperlinks = listOf(url)
                            )
                    }
                }
            }
        }
    }
}