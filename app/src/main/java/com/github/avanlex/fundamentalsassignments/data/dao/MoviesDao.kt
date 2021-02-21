package com.github.avanlex.fundamentalsassignments.data.dao

import androidx.room.*
import com.github.avanlex.fundamentalsassignments.entities.*
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieWithGenres

@Dao
interface MoviesDao {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY favorite DESC")
    fun getMovies(): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM actors WHERE movieId == :movieId")
    fun getActors(movieId: Long): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresList(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActorsList(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertMoviesList(moviesList: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreJoin(join: MovieGenreJoin)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActorJoin(join: MovieActorJoin)

    @Query("DELETE FROM movies WHERE movieId == :id")
	suspend fun deleteById(id: Long)
}