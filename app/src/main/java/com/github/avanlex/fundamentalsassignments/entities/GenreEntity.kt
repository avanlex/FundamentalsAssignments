package com.github.avanlex.fundamentalsassignments.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

import androidx.room.PrimaryKey
import com.github.avanlex.fundamentalsassignments.data.DbContract

@Entity(
    tableName = DbContract.Genres.TABLE_NAME,
    indices = [Index(DbContract.Genres.COLUMN_NAME_ID)]
)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = DbContract.Genres.COLUMN_NAME_ID)
    val genreId: Int,
    val name: String
)

