package com.github.avanlex.fundamentalsassignments.movieList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.domain.IMovieListLoader
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieListLoader: IMovieListLoader
) : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData(false)

    val movieList: LiveData<List<Movie>> get() = _mutableMovieList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    fun loadMovies() {
        if (_mutableMovieList.value?.isEmpty() == true) {
            viewModelScope.launch {
                _mutableLoadingState.value = true
                _mutableMovieList.value = movieListLoader.getMovies()
                _mutableLoadingState.value = false
            }
        }
    }
}

