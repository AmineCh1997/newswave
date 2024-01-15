package com.instantsystem.newswave.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.instantsystem.newswave.data.datasources.NewsService
import com.instantsystem.newswave.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    //.addHeader("Authorization", "Token ${Constants.TOKEN}")
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url
            val urlWithApiKey = originalUrl.newBuilder()
                .addQueryParameter("apiKey", Constants.API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()

            chain.proceed(newRequest)
        }

        val updatedOkHttpClient = okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .client(updatedOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }



    @Provides
    fun provideStarService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}