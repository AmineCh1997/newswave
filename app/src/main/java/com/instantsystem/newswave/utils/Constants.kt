package com.instantsystem.newswave.utils

import android.annotation.SuppressLint
import java.util.Locale

object Constants {
    const val SPLASH_SCREEN_DURATION = 2000L
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "e875a91b1be4440382fdba8086a169d5"

    const val CELL_COUNT = 1
    const val NEWS_KEY = "NEWS_KEY"
    @SuppressLint("ConstantLocale")
    val DEVICE_LANGUAGE = Locale.getDefault().language

}