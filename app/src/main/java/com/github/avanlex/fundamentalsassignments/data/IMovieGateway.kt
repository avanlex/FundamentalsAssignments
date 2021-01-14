package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlin.coroutines.CoroutineContext

interface IMovieGateway {
    val dispatcher: CoroutineContext
    suspend fun getMovies(): List<Movie>
    suspend fun getActors(movieId: Int): List<Actor>
}
