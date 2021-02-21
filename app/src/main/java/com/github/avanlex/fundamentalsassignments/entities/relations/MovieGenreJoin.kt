package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenreJoin(
        val movieId: Long,
        val genreId: Long
){
        constructor(movieId: Int, genreId: Int) : this(movieId.toLong(), genreId.toLong())
}