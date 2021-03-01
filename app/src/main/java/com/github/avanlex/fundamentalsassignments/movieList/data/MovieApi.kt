package com.github.avanlex.fundamentalsassignments.movieList.data

import com.github.avanlex.fundamentalsassignments.movieList.data.dto.CreditsJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenresJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MoviesJson
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/popular")
    suspend fun loadMovies(): MoviesJson

    @GET("genre/movie/list")
    suspend fun loadGenres(): GenresJson

    @GET("movie/{movie_id}/credits")
    suspend fun loadActors(@Path("movie_id") movieId : Int): CreditsJson

}