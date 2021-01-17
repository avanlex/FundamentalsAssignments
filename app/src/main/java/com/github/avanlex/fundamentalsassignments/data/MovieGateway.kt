package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieGateway(
    private val remoteDataSource: IMovieDataSource,
    private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        remoteDataSource.loadMovies()
    }

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        remoteDataSource.loadActors(movieId)
    }
}