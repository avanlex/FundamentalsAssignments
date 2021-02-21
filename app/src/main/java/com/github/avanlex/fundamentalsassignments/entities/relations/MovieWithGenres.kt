package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

data class MovieWithGenres (

    @Embedded
    var movie : MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreJoin::class)
    )
    var genres: List<GenreEntity>,

)
