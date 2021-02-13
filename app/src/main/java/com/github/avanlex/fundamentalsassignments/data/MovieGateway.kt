package com.github.avanlex.fundamentalsassignments.data

import android.util.Log
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
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


@Dao
class MovieGateway(
        private val moviesDataProvider: IMovieProvider,
        private val actorsDataProvider: IActorProvider,
        private val genresDataProvider: IGenresProvider,
        private val dataBase: DataBase,
        private val dispatcher: CoroutineDispatcher
) : IMovieGateway {

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        val movieDtoList : List<MovieJson>
        val genres : List<Genre>
        val genresMap : Map <Int, Genre>
        try {
            movieDtoList = moviesDataProvider.loadMovies()
//            val movieEntityList = movieDtoList.map{toEntity(it, 0, false)}
            //dataBase.movieDao.insert(movieEntityList)
        } catch (throwable: Throwable){
            Log.d("Gateway", "movie load error")
        }

        val genreDtoList : List<GenreJson>

        try {
            genreDtoList = genresDataProvider.loadGenres()
            genres = genreDtoList.map { Genre(id = it.genreId, name = it.name) }
            genresMap = genres.associateBy { it.id }
        } catch (throwable: Throwable){
            Log.d("Gateway", "genres load error")
        }

        emptyList()
    }

    override suspend fun getActors(movieId: Int): List<Actor> = withContext(dispatcher) {
        val actors = actorsDataProvider.loadActors(movieId)
        actors.map { Actor(it.actorId, it.name, BuildConfig.BASE_IMAGE_URL + "original" + it.profilePath) }
    }

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean = withContext(dispatcher) {
        moviesDataProvider.markAsFavorite(favorite)
    }
/*
    private fun toEntity(movies: MovieJson, runtime : Int, favorite : Boolean) = MovieWithRelations(
        MovieEntity(
            movies.movieId,
            movies.overview,
            movies.title,
            movies.posterPath,
            movies.backdropPath,
            movies.voteAverage.toFloat(),
            movies.votesCount,
            movies.adult,
            runtime,
            favorite
        ),
        emptyList(),
        emptyList()
    )

    private fun toMovieList(movieEntityList : List<MovieWithRelations>, genresMap: Map <Int, Genre>) = movieEntityList.map { item ->
        val movie = item.movie
        Movie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = BuildConfig.BASE_IMAGE_URL + "original" + item.movie.posterPath,
            backdropPath = movie.backdropPath,
            rating = (movie.rating / 2) .toFloat(),
            votesCount = movie.votesCount,
            adult = movie.adult,
            runtime = 0,
            favorite = false,
//            genres = item.gen.map {
//                genresMap[it] ?: throw IllegalArgumentException("Genre not found")
//            },
            genres = item.genres.map { Genre(it.id, it.name) },
            actors = item.actors.map { Actor(it.id, it.name, it.profilePath) }
        )
    }
*/
}

