package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

@Entity(
    primaryKeys = [DbContract.Movies.COLUMN_NAME_ID, DbContract.Genres.COLUMN_NAME_ID],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = [DbContract.Movies.COLUMN_NAME_ID],
            childColumns = [DbContract.Movies.COLUMN_NAME_ID],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = [DbContract.Genres.COLUMN_NAME_ID],
            childColumns = [DbContract.Genres.COLUMN_NAME_ID],
            onDelete = CASCADE
        )
    ]
)
data class MovieGenreJoin(
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_ID)
    val movieId: Int,
    @ColumnInfo(name = DbContract.Genres.COLUMN_NAME_ID)
    val genreId: Int
)