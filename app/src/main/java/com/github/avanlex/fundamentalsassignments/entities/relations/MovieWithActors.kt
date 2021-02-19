package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

//data class MovieWithActors (
//
////    @Embedded
////    var movie : MovieEntity,
////    @Relation(
////        parentColumn = "movieId",
////        entityColumn = "actorId",
////        associateBy = Junction(MovieActorJoin::class)
////    )
//    var actors: List<ActorEntity>
//
//)