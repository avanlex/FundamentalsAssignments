package com.github.avanlex.fundamentalsassignments.movieDetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.avanlex.fundamentalsassignments.data.IMovieGateway
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import kotlinx.coroutines.launch

class DetailsViewModel(
        private val actorListLoader: IMovieGateway
) : ViewModel() {


    private val _actorList: MutableLiveData<List<Actor>> = MutableLiveData(emptyList())
    val actorList: LiveData<List<Actor>> = _actorList

    fun loadActors(movieId: Int) {
        if (_actorList.value?.isEmpty() == true) {
            viewModelScope.launch {
                _actorList.value = actorListLoader.getActors(movieId)
            }
        }
    }

}

