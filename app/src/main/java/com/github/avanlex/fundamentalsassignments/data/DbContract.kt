package com.github.avanlex.fundamentalsassignments.data


object DbContract {
    
    const val DATABASE_NAME = "movies.db"
    
    object Genres {
        const val TABLE_NAME = "genres"
        const val COLUMN_NAME_ID = "genre_id"
    }

    object Actors {
        const val TABLE_NAME = "actors"
        const val COLUMN_NAME_ID = "actor_id"
    }

    object Movies{
        const val TABLE_NAME = "movies"
        const val COLUMN_NAME_ID = "movie_id"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_POSTER_PATH = "posterPath"
        const val COLUMN_NAME_BACKDROP_PATH = "backdropPath"
        const val COLUMN_NAME_RATING = "rating"
        const val COLUMN_NAME_VOTESCOUNT = "votesCount"
        const val COLUMN_NAME_ADULT = "adult"
        const val COLUMN_NAME_RUNTIME = "runtime"
        const val COLUMN_NAME_FAVORITE = "favorite"
    }
}
