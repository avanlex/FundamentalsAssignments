package com.github.avanlex.fundamentalsassignments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers


class MovieListViewModelFactory: ViewModelProvider.Factory  {

    private var application: Application? = null
    private var id: Long = 0

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {

        MovieListViewModel::class.java -> {

            MovieListViewModel(MovieListLoader(app = null, dispatcher = Dispatchers.Default))
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
