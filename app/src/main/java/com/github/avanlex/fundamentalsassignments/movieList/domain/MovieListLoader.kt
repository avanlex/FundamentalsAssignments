package com.github.avanlex.fundamentalsassignments.movieList.domain

import com.github.avanlex.fundamentalsassignments.App
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.loadMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieListLoader(private val dispatcher: CoroutineDispatcher) :
        IMovieListLoader {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        loadMovies(App.instance.applicationContext)
    }

}

