package com.github.avanlex.fundamentalsassignments.domain

import androidx.room.*


import com.github.avanlex.fundamentalsassignments.data.DbContract
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre

@Entity(
    tableName = DbContract.Movies.TABLE_NAME,
    indices = [Index(DbContract.Movies.COLUMN_NAME_ID)],
)
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_ID)
    var id: Int,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_OVERVIEW)
    var overview: String,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_TITLE)
    var title: String,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_POSTER_PATH)
    var posterPath: String,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_BACKDROP_PATH)
    var backdropPath: String,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_RATING)
    var rating: Float,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_VOTESCOUNT)
    var votesCount: Int,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_ADULT)
    var adult: Boolean,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_RUNTIME)
    var runtime: Int,
    @ColumnInfo(name = DbContract.Movies.COLUMN_NAME_FAVORITE)
    var favorite: Boolean,

){
    constructor() : this(0, "", "", "", "", 0F, 0, false, 0, false)
}


