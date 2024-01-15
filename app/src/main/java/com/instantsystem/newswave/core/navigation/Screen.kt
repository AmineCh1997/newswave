package com.instantsystem.newswave.core.navigation

sealed class Screen(val route: String) {
    object NewsScreen : Screen("news_screen")
    object SplashScreen : Screen("home_screen")
    object NewsDetailScreen : Screen("details_screen")
}
