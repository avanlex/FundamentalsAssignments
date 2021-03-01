package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson

interface IAccountProvider {
    suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean
}