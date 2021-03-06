package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.entities.MovieEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieWithGenres
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson

interface IMovieProvider {

    suspend fun loadMovies(): List<MovieJson>

    suspend fun dbLoadMovies(): List<MovieWithGenres>

    suspend fun insert(movie: MovieEntity)

    suspend fun insertList(moviesList: List<MovieEntity>)

    suspend fun deleteById(id: Int)
}

