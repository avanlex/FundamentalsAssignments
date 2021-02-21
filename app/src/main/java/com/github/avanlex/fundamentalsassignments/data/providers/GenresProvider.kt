package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson

class GenresProvider(private val api: MovieApi) : IGenresProvider {

    override suspend fun loadGenres(): List<GenreJson> {
        return api.loadGenres().genres
    }
}

