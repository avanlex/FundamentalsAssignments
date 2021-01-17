package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie

interface IMovieDataSource {

    suspend fun loadMovies(): List<Movie>
    suspend fun loadActors(movieId: Int):  List<Actor>

}