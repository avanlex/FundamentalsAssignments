package com.github.avanlex.fundamentalsassignments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.avanlex.fundamentalsassignments.data.dao.ActorsDao
import com.github.avanlex.fundamentalsassignments.data.dao.GenresDao
import com.github.avanlex.fundamentalsassignments.data.dao.MoviesDao
import com.github.avanlex.fundamentalsassignments.entities.*
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin

@Database(
	entities = [
		MovieEntity::class,
		GenreEntity::class,
		MovieGenreJoin::class,
		ActorEntity::class,
		MovieActorJoin::class
 	],
	version = 1,
	exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){

	abstract val moviesDao : MoviesDao
	abstract val actorsDao : ActorsDao
	abstract val genresDao : GenresDao

	companion object {
		fun create(application: Context): MovieDatabase {
			return Room.databaseBuilder(application,
				MovieDatabase::class.java,
				DbContract.DATABASE_NAME
			).fallbackToDestructiveMigration()
			 .build()
		}
	}
}