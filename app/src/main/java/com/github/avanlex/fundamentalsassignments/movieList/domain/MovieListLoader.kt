package com.github.avanlex.fundamentalsassignments.movieList.domain

import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

//class MovieListLoader(private val context: Context, private val  dispatcher: CoroutineDispatcher) :
class MovieListLoader(private val  dispatcher: CoroutineDispatcher) :
        IMovieListLoader {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        emptyList()
        //loadMovies()
    }

}
/*
private fun loadCats() {
    coroutineScope.launch(exceptionHandler) {
        val cats = RetrofitModule.catsApi.getCats()
        showRandomCat(cats)
    }
}*/