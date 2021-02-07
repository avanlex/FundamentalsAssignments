package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson

interface IMovieProvider {

    suspend fun loadMovies(): List<MovieJson>
    suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean

}