package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieGateway(
    private val localDataSource: IMovieDataSource,
    private val remoteDataSource: IMovieDataSource,
    override val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        if (localDataSource==null){
            remoteDataSource.loadMovies()
        } else
            localDataSource.loadMovies()
    }
}