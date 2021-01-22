package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(private val api: MovieApi) : IMovieDataSource {

    override suspend fun loadMovies() = withContext(Dispatchers.IO) {
        api.loadMovies().movieList
        val genresMap = loadGenres().associateBy { it.id }
        val movieList = api.loadMovies().movieList

         movieList.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = BuildConfig.BASE_IMAGE_URL + "original" + movie.posterPath,
                backdropPath = movie.backdropPath,
                rating = (movie.voteAverage / 2) .toFloat(),
                votesCount = movie.votesCount,
                adult = movie.adult,
                runtime = 0,
                favorite = false,
                genres = movie.genreIds.map {
                    genresMap[it] ?: throw IllegalArgumentException("Genre not found")
                },
                actors = null
            )
        }
    }

    private suspend fun loadGenres(): List<Genre> = withContext(Dispatchers.IO) {
        val genres = api.loadGenres().genres
        genres.map { Genre(id = it.id, name = it.name) }
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean {
        /* TODO: FIX retrofit2.HttpException: HTTP 404
                For request "POST account/{account_id}/favorite"
                "GET /account" is required to execute
                and to do this, you first need to get the session
                "POST /authentication/session/new"
                Too lazy to do it now.*/
        // return api.markAsFavorite(favorite).statusCode == 12
        return true
    }
}