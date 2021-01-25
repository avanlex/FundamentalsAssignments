package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson

interface IGenresProvider {
    suspend fun loadGenres(): List<GenreJson>
}