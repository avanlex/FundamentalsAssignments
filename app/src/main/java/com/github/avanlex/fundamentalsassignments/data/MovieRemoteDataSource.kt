package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Credits
import com.github.avanlex.fundamentalsassignments.movieList.data.GenresJson
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.MoviesPage

class MovieRemoteDataSource(retrofit: MovieApi) : MovieApi {
    override suspend fun loadMovies(): MoviesPage {
        TODO("Not yet implemented")
    }

    override suspend fun loadGenres(): GenresJson {
        TODO("Not yet implemented")
    }

    override suspend fun loadActors(movieId: Int): Credits {
        TODO("Not yet implemented")
    }

}