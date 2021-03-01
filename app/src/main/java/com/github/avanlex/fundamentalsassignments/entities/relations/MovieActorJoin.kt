package com.github.avanlex.fundamentalsassignments.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity

@Entity(primaryKeys = [DbContract.Movies.COLUMN_NAME_ID, DbContract.Actors.COLUMN_NAME_ID],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = [DbContract.Movies.COLUMN_NAME_ID],
            childColumns = [DbContract.Movies.COLUMN_NAME_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = [DbContract.Actors.COLUMN_NAME_ID],
            childColumns = [DbContract.Actors.COLUMN_NAME_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieActorJoin(
        @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_ID)
        val movieId: Int,
        @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_ID)
        val actorId: Int
)
