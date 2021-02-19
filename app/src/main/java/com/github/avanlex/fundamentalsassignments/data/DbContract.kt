package com.github.avanlex.fundamentalsassignments.data

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre

object DbContract {
    
    const val DATABASE_NAME = "movies.db"
    
    object Genres {
        const val TABLE_NAME = "genres"
//        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ID = "genreId"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_LATITUDE = "latitude"
        const val COLUMN_NAME_LONGITUDE = "longitude"
    }

    object Actors {
        const val TABLE_NAME = "actors"
//        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ID = "actorId"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PROFILE_PATH = "profile_path"
    }

    object Movies{
        const val TABLE_NAME = "movies"
//        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ID = "movieId"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_POSTER_PATH = "posterPath"
        const val COLUMN_NAME_BACKDROP_PATH = "backdropPath"
        const val COLUMN_NAME_RATING = "rating"
        const val COLUMN_NAME_VOTESCOUNT = "votesCount"
        const val COLUMN_NAME_ADULT = "adult"
        const val COLUMN_NAME_RUNTIME = "runtime"
        const val COLUMN_NAME_FAVORITE = "favorite"
        const val COLUMN_NAME_GENRES = "genres"
        const val COLUMN_NAME_ACTORS = "actors"
    }
}
