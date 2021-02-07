package com.github.avanlex.fundamentalsassignments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.avanlex.fundamentalsassignments.domain.MovieEntity

@Dao
interface MoviesDao {

//    @Query("SELECT * FROM movies ORDER BY _id ASC")
//	suspend fun getAll() : List<MovieEntity>
//
//    @Insert
//	suspend fun insert(movie: MovieEntity)
//
//    @Query("DELETE FROM movies WHERE _id == :id")
//	suspend fun deleteById(id: Long)
}