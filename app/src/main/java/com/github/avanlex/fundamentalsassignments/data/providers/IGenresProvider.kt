package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson


interface IGenresProvider {

    suspend fun loadGenres(): List<GenreJson>

    suspend fun dbInsertMovieGenreJoin(join: MovieGenreJoin)
    suspend fun dbInsertList(genres: List<GenreEntity>)
}