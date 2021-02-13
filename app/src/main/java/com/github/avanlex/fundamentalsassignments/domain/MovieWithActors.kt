package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.*
import com.github.avanlex.fundamentalsassignments.data.DbContract

data class MovieWithActors (
    @Embedded
    var movie : MovieEntity,
    @Relation(
        parentColumn = DbContract.Movies.COLUMN_NAME_ID,
        entity = ActorEntity::class,
        entityColumn = DbContract.Actors.COLUMN_NAME_ID,
        associateBy = Junction(
            value = MovieActor::class,
            parentColumn = "movieId",
            entityColumn = "actorId"
        )
    )
    val guests: List<ActorEntity>
)