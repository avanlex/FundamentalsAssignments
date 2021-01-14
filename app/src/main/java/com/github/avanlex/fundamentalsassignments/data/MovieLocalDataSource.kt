package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.CreditsJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenresJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MoviesJson

class MovieLocalDataSource: MovieApi {

    override suspend fun loadMovies(): MoviesJson {
        TODO("Not yet implemented")
    }

    override suspend fun loadGenres(): GenresJson {
        TODO("Not yet implemented")
    }

    override suspend fun loadActors(movieId: Int): CreditsJson {
        TODO("Not yet implemented")
    }
}