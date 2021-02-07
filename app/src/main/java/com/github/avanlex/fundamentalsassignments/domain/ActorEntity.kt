package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.avanlex.fundamentalsassignments.data.DbContract

@Entity(
    tableName = DbContract.Actors.TABLE_NAME,
    indices = [Index(DbContract.Actors.COLUMN_NAME_ID)]
)
data class ActorEntity (
    @PrimaryKey
    @ColumnInfo(name = DbContract.Actors.COLUMN_NAME_ID)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "profile_path")
    val profilePath: String? = null
)
