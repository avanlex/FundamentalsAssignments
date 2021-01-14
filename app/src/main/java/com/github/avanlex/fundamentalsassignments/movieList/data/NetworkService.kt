package com.github.avanlex.fundamentalsassignments.movieList.data

import com.github.avanlex.fundamentalsassignments.ApiConfig
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.*

@Serializable
data class AuthTmdb(
    @SerialName("success")
    val success: Boolean,
    @SerialName("guest_session_id")
    val session_id: String,
    @Contextual
    val expires_at: Date
)


object NetworkService {

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
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val jsonApi = retrofit.create(MovieApi::class.java)
}
