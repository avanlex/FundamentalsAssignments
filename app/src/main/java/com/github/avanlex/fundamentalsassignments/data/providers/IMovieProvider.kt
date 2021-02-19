package com.github.avanlex.fundamentalsassignments.data.providers

import android.provider.ContactsContract
import com.github.avanlex.fundamentalsassignments.data.DataBase
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson

interface IMovieProvider {

    val dataBase : DataBase
    suspend fun loadMovies(): List<MovieJson>
    suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean

}