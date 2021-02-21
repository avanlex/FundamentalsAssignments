package com.github.avanlex.fundamentalsassignments.data

import android.util.Log
import androidx.room.Dao
import com.github.avanlex.fundamentalsassignments.BuildConfig
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
        private val moviesDataProvider: IMovieProvider,
        private val actorsDataProvider: IActorProvider,
        private val genresDataProvider: IGenresProvider,
        private val dispatcher: CoroutineDispatcher,
        private val dataBase: DataBase
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {

        val movieDtoList: List<MovieJson>
        val genreDtoList: List<GenreJson>
        val genreEntityList: List<GenreEntity>

        try {
            movieDtoList = moviesDataProvider.loadMovies()
            movieDtoList.map{ movieJson ->
                movieJson.genreIds.map { genreId ->
                    val movieGenreJoin = MovieGenreJoin( movieJson.movieId, genreId )
                    dataBase.moviesDao.insertMovieGenreJoin(movieGenreJoin)
                }
                dataBase.moviesDao.insert(movieWebDtoToEntity(movieJson))
            }
        } catch (e: HttpException) {
            Log.d(TAG, "Movies Json load error")
        }

        try {
            genreDtoList = genresDataProvider.loadGenres()
            genreEntityList = genresWebDtoToEntityList(genreDtoList)
            dataBase.moviesDao.insertGenresList(genreEntityList)
        } catch (e: HttpException) {
            Log.d(TAG, "Genres Json load error")
        }

        val movieDbEntityList = dataBase.moviesDao.getMovies()
        movieEntityListToPojoList(movieDbEntityList)
    }

    private fun movieEntityListToPojoList(movieDbItemList: List<MovieWithGenres>) : List<Movie> =
    movieDbItemList.map{ movieEntity ->
        with(movieEntity) {
            Movie(
                id = movie.id,
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

    private fun movieWebDtoToEntity(movies: MovieJson) = MovieEntity(
            movies.movieId,
            movies.overview,
            movies.title,
            movies.posterPath,
            movies.backdropPath,
            movies.voteAverage,
            movies.votesCount,
            movies.adult,
            0,
            false
    )

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        try {
            val actors: List<ActorJson> = actorsDataProvider.loadActors(movieId)

            dataBase.moviesDao.insertActorsList(actors.map {
                ActorEntity(
                    it.actorId,
                    it.name,
                    BuildConfig.BASE_IMAGE_URL + it.profilePath,
                    movieId
                )
            })
        } catch (e: HttpException) {
            Log.d(TAG, "Actor Json load error")
        }

        val actorEntityList = dataBase.moviesDao.getActors(movieId.toLong())
        actorEntityList.map { Actor(
            it.id,
            it.name,
            it.profilePath
        )}
    }

    private fun genresWebDtoToEntityList(genres: List<GenreJson>) = genres.map{
        GenreEntity(
                it.genreId,
                it.name
        )
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean = withContext(dispatcher) {
        moviesDataProvider.markAsFavorite(favorite)
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}
