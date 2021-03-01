package com.github.avanlex.fundamentalsassignments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin

@Dao
interface GenresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreJoin(join: MovieGenreJoin)

    @Query("DELETE FROM genres WHERE ${DbContract.Genres.COLUMN_NAME_ID} == :id")
    suspend fun deleteById(id: Int)
}