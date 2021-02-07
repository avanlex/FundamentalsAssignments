package com.github.avanlex.fundamentalsassignments.data

import androidx.room.Dao
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.data.providers.IActorProvider
import com.github.avanlex.fundamentalsassignments.data.providers.IGenresProvider
import com.github.avanlex.fundamentalsassignments.data.providers.IMovieProvider
import com.github.avanlex.fundamentalsassignments.domain.MovieEntity
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


@Dao
class MovieGateway(
        private val moviesDataProvider: IMovieProvider,
        private val actorsDataProvider: IActorProvider,
        private val genresDataProvider: IGenresProvider,
        private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        val movieDtoList = moviesDataProvider.loadMovies()
        val genreDtoList = genresDataProvider.loadGenres()
        val genres = genreDtoList.map { Genre(id = it.genreId, name = it.name) }
        val genresMap = genres.associateBy { it.id }

        movieDtoList.map { movie ->
            Movie(
                    id = movie.movieId,
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
        val actors = actorsDataProvider.loadActors(movieId)
        actors.map { Actor(it.actorId, it.name, BuildConfig.BASE_IMAGE_URL + "original" + it.profilePath) }
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean = withContext(dispatcher) {
        moviesDataProvider.markAsFavorite(favorite)
    }

//    private fun toEntity(movies: MovieJson) = MovieEntity(
//
//    )

    //private fun toLocation(entity: MovieEntity) = Movie()
}
