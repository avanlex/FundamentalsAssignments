package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.Entity

@Entity(
    primaryKeys = ["movieId", "actorId"]
)
data class MovieActor(
    val movieId: Long,
    val actorId: Long
)