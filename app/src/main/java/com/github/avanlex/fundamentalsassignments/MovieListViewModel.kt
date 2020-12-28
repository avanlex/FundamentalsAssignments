package com.github.avanlex.fundamentalsassignments

import android.content.Context
import androidx.lifecycle.*
import com.github.avanlex.fundamentalsassignments.data.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieListLoader: IMovieListLoader
) : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData<Boolean>(false)

    val movieList: LiveData<List<Movie>> get() = _mutableMovieList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    fun loadMovies() {
        viewModelScope.launch {
            _mutableLoadingState.value = true
            _mutableMovieList.value = movieListLoader.getMovies()
            _mutableLoadingState.value = false
        }
    }
}

