package com.github.avanlex.fundamentalsassignments.movieList.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers


class MovieListViewModelFactory: ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieListViewModel::class.java ->
            MovieListViewModel(MovieListLoader(dispatcher = Dispatchers.Default))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}
