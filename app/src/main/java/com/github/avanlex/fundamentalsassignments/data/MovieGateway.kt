package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieGateway(
    private val moviesDataSource: IMovieDataSource,
    private val actorsDataSource: IActorDataSource,
    private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        moviesDataSource.loadMovies()
    }

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        actorsDataSource.loadActors(movieId)
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean {
        return moviesDataSource.markAsFavorite(favorite)
    }
}