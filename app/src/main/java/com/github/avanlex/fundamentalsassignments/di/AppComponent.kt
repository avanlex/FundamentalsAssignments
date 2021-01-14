package com.github.avanlex.fundamentalsassignments.di

import com.github.avanlex.fundamentalsassignments.ApiConfig
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppComponent {
    val okHttpClient: OkHttpClient
}

object AppModule : AppComponent {
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    override val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()

//    val moshi = Moshi.Builder().build()
//
//    val retrofit = Retrofit.Builder()
//        .client(okHttpClient)
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .baseUrl("http://httpbin.org")
//        .build()

//    val api = Retrofit.Builder()
//        .baseUrl(BuildConfig.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(MoviesApi::class.java)

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(TmdbApiHeaderInterceptor())
        .build()

    private class TmdbApiHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("api_key", ApiConfig.apiKey)
                .build();

            val request = originalRequest.newBuilder()
                .url(originalHttpUrl)
                .build()

            return chain.proceed(request)
        }
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val api = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(MovieApi::class.java)
}