package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson

interface IMovieDataSource {

    suspend fun loadMovies(): List<Movie>
    suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean

}