package com.instantsystem.newswave.core.navigation

import androidx.compose.runtime.Composable
import com.instantsystem.newswave.data.models.SingleNewsResponse
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.instantsystem.newswave.features.news.view.NewsScreen
import com.instantsystem.newswave.features.newsDetails.view.DetailsScreen
import com.instantsystem.newswave.utils.Constants.NEWS_KEY
import com.instantsystem.newswave.features.splashScreen.SplashScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.NewsScreen.route) {


            NewsScreen(navController = navController)
        }
        composable(Screen.NewsDetailScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<SingleNewsResponse>(NEWS_KEY).let {
                if (it != null) {
                    DetailsScreen(singleNewsResponse = it)
                }
            }
        }
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}