package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

data class MovieWithGenres (

    @Embedded
    var movie : MovieEntity,
    @Relation(
        parentColumn = DbContract.Movies.COLUMN_NAME_ID,
        entityColumn = DbContract.Genres.COLUMN_NAME_ID,
        associateBy = Junction(MovieGenreJoin::class)
    )
    var genres: List<GenreEntity>,

)
