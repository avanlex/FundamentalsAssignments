package com.github.avanlex.fundamentalsassignments.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.data.MovieDatabase
import com.github.avanlex.fundamentalsassignments.data.MovieGateway
import com.github.avanlex.fundamentalsassignments.data.providers.AccountProvider
import com.github.avanlex.fundamentalsassignments.data.providers.ActorsProvider
import com.github.avanlex.fundamentalsassignments.data.providers.GenresProvider
import com.github.avanlex.fundamentalsassignments.data.providers.MoviesProvider
import com.github.avanlex.fundamentalsassignments.movieDetails.presentation.DetailsViewModel
import com.github.avanlex.fundamentalsassignments.movieDetails.presentation.MovieDetailsViewModelFactory
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.presentation.MovieListViewModelFactory
import com.github.avanlex.fundamentalsassignments.movieList.presentation.MoviesViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

// Container of objects shared across the whole app
class AppContainer(applicationContext: Context) {

    private val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
    private val cache = Cache(applicationContext.cacheDir, cacheSize)

    private var onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
        response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
    }

    private var apiKeyInsertInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

        val request = originalRequest.newBuilder()
                .url(originalHttpUrl)
                .build()
        chain.proceed(request)
    }

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(apiKeyInsertInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .cache(cache)
        .build()

    private val applicationJson = "application/json".toMediaType()

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
    @Suppress("EXPERIMENTAL_API_USAGE")
    private val movieApi = Retrofit.Builder()
        .client(this.client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory(applicationJson))
        .build()
        .create<MovieApi>()

    private val database = MovieDatabase.create(applicationContext)

    private val movieProvider = MoviesProvider(movieApi, database.moviesDao)
    private val actorsProvider = ActorsProvider(movieApi, database.actorsDao)
    private val genresProvider = GenresProvider(movieApi, database.genresDao)
    private val accountProvider = AccountProvider(movieApi, database.moviesDao)

    // userRepository is not private; it'll be exposed
    @Suppress("MemberVisibilityCanBePrivate")
    val movieGateway by lazy {
        MovieGateway(
                accountProvider,
                movieProvider,
                actorsProvider,
                genresProvider,
                database.moviesDao,
                database.genresDao,
                database.actorsDao,
                Dispatchers.IO,
        )
    }

    fun getMoviesViewModel(fragment: Fragment): MoviesViewModel =
         ViewModelProvider(fragment, MovieListViewModelFactory(movieGateway)).get()

    fun getDetailsViewModel(fragment: Fragment): DetailsViewModel =
            ViewModelProvider(fragment, MovieDetailsViewModelFactory(movieGateway)).get()

}
