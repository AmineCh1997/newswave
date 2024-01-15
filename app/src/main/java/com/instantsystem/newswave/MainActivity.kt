package com.instantsystem.newswave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.instantsystem.newswave.core.navigation.Navigation
import com.instantsystem.newswave.core.theme.NewsWaveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsWaveTheme {
                Navigation()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsWaveTheme {
        Navigation()
    }
}
