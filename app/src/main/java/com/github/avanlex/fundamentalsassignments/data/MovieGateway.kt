package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieGateway(
        private val moviesDataSource: IMovieProvider,
        private val actorsDataSource: IActorProvider,
        private val genresDataSource: IGenresProvider,
        private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        val movieDtoList = moviesDataSource.loadMovies()
        val genreDtoList = genresDataSource.loadGenres()
        val genres = genreDtoList.map { Genre(id = it.id, name = it.name) }
        val genresMap = genres.associateBy { it.id }

        movieDtoList.map { movie ->
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

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        val actors = actorsDataSource.loadActors(movieId)
        actors.map { Actor(it.id, it.name, BuildConfig.BASE_IMAGE_URL + "original" + it.picture) }
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean = withContext(dispatcher) {
        moviesDataSource.markAsFavorite(favorite)
    }
}