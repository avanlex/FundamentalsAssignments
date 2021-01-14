package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlin.coroutines.CoroutineContext

interface IMovieGateway {
    abstract val dispatcher: CoroutineContext

    suspend fun getMovies(): List<Movie>
}
