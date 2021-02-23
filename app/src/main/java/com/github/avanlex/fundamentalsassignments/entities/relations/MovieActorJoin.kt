package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Entity
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

@Entity(primaryKeys = ["movieId", "actorId"],
//    foreignKeys = [
//        androidx.room.ForeignKey(
//            entity = MovieEntity::class,
//            parentColumns = ["movieId"],
//            childColumns = ["movieId"],
//            onDelete = androidx.room.ForeignKey.CASCADE
//        ),
//        androidx.room.ForeignKey(
//            entity = ActorEntity::class,
//            parentColumns = ["actorId"],
//            childColumns = ["actorId"],
//            onDelete = androidx.room.ForeignKey.CASCADE
//        )
//    ]
)
data class MovieActorJoin(
        val movieId: Long,
        val actorId: Long
)