package com.github.avanlex.fundamentalsassignments.movieList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.avanlex.fundamentalsassignments.data.IMovieGateway

class MovieListViewModelFactory(
        private val movieListLoader: IMovieGateway
): ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesViewModel::class.java ->
            MoviesViewModel(movieListLoader)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}
