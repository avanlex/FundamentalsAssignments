package com.github.avanlex.fundamentalsassignments.data

import androidx.room.Dao
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson

@Dao
interface IMovieGateway {

    suspend fun getMovies(): List<Movie>
    suspend fun getActors(movieId: Int): List<Actor>
    suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean

}
