package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieGenresActorsCrossEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val movieId: Long,
    val genreId: Long,
    val actorId: Long
)