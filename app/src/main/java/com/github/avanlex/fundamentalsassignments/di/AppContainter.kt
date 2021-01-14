package com.github.avanlex.fundamentalsassignments.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.data.MovieGateway
import com.github.avanlex.fundamentalsassignments.data.MovieLocalDataSource
import com.github.avanlex.fundamentalsassignments.data.MovieRemoteDataSource
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.presentation.MovieListViewModelFactory
import com.github.avanlex.fundamentalsassignments.movieList.presentation.MoviesViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

// Container of objects shared across the whole app
class AppContainer {

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
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();

            val request = originalRequest.newBuilder()
                .url(originalHttpUrl)
                .build()

            return chain.proceed(request)
        }
    }

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .client(this.client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(this.json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<MovieApi>() // .create<MovieApi::class.java>

    @ExperimentalSerializationApi
    private val remoteDataSource = MovieRemoteDataSource(retrofit)
    private val localDataSource = MovieLocalDataSource()

    // userRepository is not private; it'll be exposed
//    val movieGateway: MovieGateway = MovieGateway(localDataSource, remoteDataSource, Dispatchers.Default)
    @ExperimentalSerializationApi
    val movieGateway by lazy { MovieGateway(localDataSource, remoteDataSource, Dispatchers.Default) }

    @ExperimentalSerializationApi
    fun getMoviesViewModel(fragment: Fragment): MoviesViewModel =
         ViewModelProvider(fragment, MovieListViewModelFactory(movieGateway)).get() // .get(MoviesViewModel::class.java)

}
