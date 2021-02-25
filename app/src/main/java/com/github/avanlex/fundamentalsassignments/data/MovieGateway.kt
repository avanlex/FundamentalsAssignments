package com.github.avanlex.fundamentalsassignments.data

import android.util.Log
import androidx.room.Dao
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.data.dao.ActorsDao
import com.github.avanlex.fundamentalsassignments.data.dao.GenresDao
import com.github.avanlex.fundamentalsassignments.data.dao.MoviesDao
import com.github.avanlex.fundamentalsassignments.data.providers.AccountProvider
import com.github.avanlex.fundamentalsassignments.data.providers.IActorProvider
import com.github.avanlex.fundamentalsassignments.data.providers.IGenresProvider
import com.github.avanlex.fundamentalsassignments.data.providers.IMovieProvider
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieWithGenres
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.ActorJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@Dao
class MovieGateway(
    private val accountProvider: AccountProvider,
    private val moviesDataProvider: IMovieProvider,
    private val actorsDataProvider: IActorProvider,
    private val genresDataProvider: IGenresProvider,
    private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        try {
            val movieDtoList = moviesDataProvider.loadMovies()
            movieDtoList.map{ movieJson ->
                moviesDataProvider.insert(movieJson.convertToEntity())
                movieJson.genreIds.map { genreId ->
                    val movieGenreJoin = MovieGenreJoin( movieJson.movieId, genreId )
                    try {
                        genresDataProvider.dbInsertMovieGenreJoin(movieGenreJoin)
                    } catch (e: Exception) {
                        Log.wtf(TAG, e.stackTraceToString())
                    }
                }
            }

            val genreDtoList = genresDataProvider.loadGenres()
            genresDataProvider.dbInsertList(genreDtoList.mapToEntityList())
        } catch (e: Exception) {
            Log.wtf(TAG, e.stackTraceToString())
        }

        moviesDataProvider.dbLoadMovies().toPojoList()
    }

    private fun List<MovieWithGenres>.toPojoList() : List<Movie> =
        this.map{ movieEntity ->
            with(movieEntity) {
                Movie(
                    id = movie.movieId,
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = BuildConfig.BASE_IMAGE_URL + movie.posterPath,
                    backdropPath = movie.backdropPath,
                    rating = movie.rating / 2,
                    votesCount = movie.votesCount,
                    adult = movie.adult,
                    runtime = 0,
                    favorite = false,
                    genres = genres.map {genre -> Genre(genre.genreId, genre.name) },
                    actors = null
                )
            }
        }

    private fun MovieJson.convertToEntity() =
        MovieEntity(
            this.movieId,
            this.overview,
            this.title,
            this.posterPath,
            this.backdropPath,
            this.voteAverage,
            this.votesCount,
            this.adult,
            0,
            false
        )

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        try {
            val actors: List<ActorJson> = actorsDataProvider.loadActorsFromNet(movieId)

            actorsDataProvider.dbInsertList(actors.map {
                ActorEntity(
                    it.actorId,
                    it.name,
                    BuildConfig.BASE_IMAGE_URL + it.profilePath,
                    movieId
                )
            })
        } catch (e: HttpException) {
            Log.e(TAG, "Actor Json load error")
        }

        val actorEntityList = actorsDataProvider.dbLoadActors(movieId)
        actorEntityList.map { Actor(
            it.id,
            it.name,
            it.profilePath
        )}
    }

    private fun List<GenreJson>.mapToEntityList() =
        this.map{ GenreEntity( it.genreId, it.name ) }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean = withContext(dispatcher) {
        accountProvider.markAsFavorite(favorite)
    }

    companion object {
        private val TAG = MovieGateway::class.java.simpleName
    }

}
