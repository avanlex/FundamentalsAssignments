package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.Embedded
import androidx.room.Relation
import com.github.avanlex.fundamentalsassignments.data.DbContract

data class MovieWithRelations (

    @Embedded
    var movie : MovieEntity,
    @Relation(parentColumn = DbContract.Movies.COLUMN_NAME_ID, entityColumn = DbContract.Genres.COLUMN_NAME_ID)
    var genres: List<GenreEntity>,
    @Relation(parentColumn = DbContract.Movies.COLUMN_NAME_ID, entityColumn = DbContract.Actors.COLUMN_NAME_ID)
    var actors: List<ActorEntity>

)