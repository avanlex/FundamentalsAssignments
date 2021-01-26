package com.github.avanlex.fundamentalsassignments.movieDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.avanlex.fundamentalsassignments.data.IMovieGateway

class MovieDetailsViewModelFactory(
        private val actorListLoader: IMovieGateway
): ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        DetailsViewModel::class.java -> DetailsViewModel(actorListLoader)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}