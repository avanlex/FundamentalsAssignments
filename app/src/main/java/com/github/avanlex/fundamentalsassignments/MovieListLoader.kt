package com.github.avanlex.fundamentalsassignments

import com.github.avanlex.fundamentalsassignments.data.Movie
import com.github.avanlex.fundamentalsassignments.data.loadMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieListLoader(private val dispatcher: CoroutineDispatcher) :
    IMovieListLoader {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        loadMovies(App.instance.applicationContext)
    }

}

