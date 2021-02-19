package com.github.avanlex.fundamentalsassignments.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey
import com.github.avanlex.fundamentalsassignments.data.DbContract

@Entity
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = DbContract.Genres.COLUMN_NAME_ID)
    val genreId: Int,
    val name: String
)

