package com.github.avanlex.fundamentalsassignments.movieList.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private suspend fun loadGenres(dataSource: MovieApi): List<Genre> = withContext(Dispatchers.IO) {
    val genres = dataSource.loadGenres().genres
    genres.map { Genre(id = it.id, name = it.name) }
}
private suspend fun loadActors(dataSource: MovieApi, movieId: Int): List<Actor> = withContext(Dispatchers.IO) {
    val actors = dataSource.loadActors(movieId).cast
    actors.map { Actor(it.castId, it.name, it.profilePath) }
}

@Suppress("unused")
internal suspend fun loadMovies(dataSource: MovieApi): List<Movie> = withContext(Dispatchers.IO) {
    val genresMap =  loadGenres(dataSource)
    parseMovies(dataSource, genresMap)
}

internal suspend fun parseMovies(
    movieSource: MovieApi,
    genres: List<Genre>,
): List<Movie> {
    val genresMap = genres.associateBy { it.id }
    val movieList = movieSource.loadMovies().movieList

    return movieList.map { movie ->
        Movie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            poster = movie.posterPath,
            backdrop = movie.backdropPath,
            ratings = (movie.voteAverage / 2) .toFloat(),
            numberOfRatings = movie.voteCount,
            minimumAge = if (movie.adult) 18 else 0,
            runtime = 0,
            favorite = false,
            genres = movie.genreIds.map {
                genresMap[it] ?: throw IllegalArgumentException("Genre not found")
            },
            actors = null
        )
    }
}
