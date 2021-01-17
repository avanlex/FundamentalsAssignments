package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie

interface IMovieGateway {
    suspend fun getMovies(): List<Movie>
    suspend fun getActors(movieId: Int): List<Actor>
}
