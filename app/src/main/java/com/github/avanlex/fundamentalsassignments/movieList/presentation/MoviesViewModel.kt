package com.github.avanlex.fundamentalsassignments.movieList.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.avanlex.fundamentalsassignments.data.IMovieGateway
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesGateway: IMovieGateway
) : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    private val _mutableLoadingState = MutableLiveData(false)
    private val _mutableAddToFavorite = MutableLiveData(-1)

    val movieList: LiveData<List<Movie>> get() = _mutableMovieList
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState
    val addToFavorite: LiveData<Int> get() = _mutableAddToFavorite

    fun loadMovies() {
        if (_mutableMovieList.value?.isEmpty() == true) {
            viewModelScope.launch {
                try {
                    _mutableLoadingState.value = true
                    _mutableMovieList.value = moviesGateway.getMovies()
                }catch (throwable: Throwable){
                    Log.d("MoviesViewModel", "Movie List Loading Error")
                }
                    _mutableLoadingState.value = false
            }
        }
    }

    fun addToFavorite(movie: Movie, position: Int){
        viewModelScope.launch {
            val favorite = FavoriteMovieJson( movie.id, !movie.favorite, "movie")

            try {
                if (moviesGateway.markAsFavorite(favorite)){
                    movie.favorite = !movie.favorite
                    _mutableAddToFavorite.value = position
                }
            }catch (throwable: Throwable){
                Log.d("MoviesViewModel", "Mark As Favorite Request Failed")
            }
        }
    }
}

