package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRemoteDataSource(private val retrofit: MovieApi) : IMovieDataSource {
    override suspend fun loadMovies() = withContext(Dispatchers.IO) {
        retrofit.loadMovies().movieList
        val genresMap = loadGenres().associateBy { it.id }
        val movieList = retrofit.loadMovies().movieList

         movieList.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.posterPath,
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
        val genres = retrofit.loadGenres().genres
        genres.map { Genre(id = it.id, name = it.name) }
    }

    override suspend fun loadActors(movieId: Int):  List<Actor> = withContext(Dispatchers.IO) {
        val actors = retrofit.loadActors(movieId).cast
        actors.map { Actor(it.id, it.name, it.picture) }
    }

}