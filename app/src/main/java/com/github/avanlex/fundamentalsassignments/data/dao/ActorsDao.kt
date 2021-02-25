package com.github.avanlex.fundamentalsassignments.data.dao

import androidx.room.*
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin

@Dao
interface ActorsDao {
    @Transaction
    @Query("SELECT * FROM actors WHERE movieId == :movieId")
    suspend fun getByMovieId(movieId: Int): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieActorJoin(join: MovieActorJoin)

    @Query("DELETE FROM actors WHERE ${DbContract.Actors.COLUMN_NAME_ID} == :id")
    suspend fun deleteById(id: Int)
}