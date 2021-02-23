package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

@Entity(
    primaryKeys = ["movieId", "genreId"],
//    foreignKeys = [
//        ForeignKey(
//            entity = MovieEntity::class,
//            parentColumns = ["movieId"],
//            childColumns = ["movieId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = GenreEntity::class,
//            parentColumns = ["genreId"],
//            childColumns = ["genreId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)
data class MovieGenreJoin(
    val movieId: Long,
    val genreId: Long
)