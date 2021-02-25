package com.github.avanlex.fundamentalsassignments.data.dao

import androidx.room.*
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieWithGenres

@Dao
interface MoviesDao {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY favorite DESC")
    suspend fun getMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertMoviesList(moviesList: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE ${DbContract.Movies.COLUMN_NAME_ID} == :id")
	suspend fun deleteById(id: Int)
}