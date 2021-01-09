package com.github.avanlex.fundamentalsassignments.movieList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.avanlex.fundamentalsassignments.movieList.domain.MovieListLoader


class MovieListViewModelFactory(
        private val movieListLoader: MovieListLoader
): ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieListViewModel::class.java ->
            MovieListViewModel(movieListLoader)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}
