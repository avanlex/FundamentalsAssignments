package com.github.avanlex.fundamentalsassignments.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.avanlex.fundamentalsassignments.data.DbContract

@Entity(
    tableName = DbContract.Actors.TABLE_NAME,
)
data class ActorEntity (
    @PrimaryKey
    @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_ID)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
    val movieId: Int
)
