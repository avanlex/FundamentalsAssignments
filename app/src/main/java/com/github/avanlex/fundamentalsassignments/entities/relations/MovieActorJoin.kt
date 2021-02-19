package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "actorId"])
data class MovieActorJoin(
        val movieId: Long,
        val actorId: Long
)