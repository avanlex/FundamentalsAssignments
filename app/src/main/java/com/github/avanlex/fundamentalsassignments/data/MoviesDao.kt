package com.github.avanlex.fundamentalsassignments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.avanlex.fundamentalsassignments.domain.MovieActor
import com.github.avanlex.fundamentalsassignments.domain.MovieEntity
import com.github.avanlex.fundamentalsassignments.domain.MovieWithActors

@Dao
interface MoviesDao {
    @Transaction
    @Query("SELECT * FROM movies ORDER BY rating DESC")
	suspend fun getAllMovies() : List<MovieEntity>

    @Transaction
    @Query("SELECT * FROM MovieActor ORDER BY movieId ASC")
    suspend fun getAllMoviesWithActors() : List<MovieActor>


//    @Insert
//	suspend fun insert(movie: MovieWithActors)

//    @Insert
//    suspend fun insert(movies: List<MovieWithRelations>)

    @Query("DELETE FROM movies WHERE _id == :id")
	suspend fun deleteById(id: Long)
}