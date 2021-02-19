package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

data class MovieWithGenresActors (

    @Embedded
    var movie : MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreJoin::class)
    )
    var genres: List<GenreEntity>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        associateBy = Junction(MovieActorJoin::class)
    )
    var actors: List<ActorEntity>

)

/*
data class MovieWithRelations (

    @Embedded
    var movie : MovieEntity,
    @Relation(
//            parentColumn = DbContract.Movies.COLUMN_NAME_ID,
//            entityColumn = DbContract.Genres.COLUMN_NAME_ID,
//            entity = GenreEntity::class,
            parentColumn = "movieId",
            entityColumn = "genreId",
            associateBy = Junction(MovieGenreCrossRef::class)
    )
    var genres: List<GenreEntity>,
    @Relation(
//            parentColumn = DbContract.Movies.COLUMN_NAME_ID,
//            entityColumn = DbContract.Actors.COLUMN_NAME_ID,
//            entity = ActorEntity::class,
            parentColumn = "movieId",
            entityColumn = "actorId",
            associateBy = Junction(MovieActorCrossRef::class)
    )
    var actors: List<ActorEntity>

)*/
