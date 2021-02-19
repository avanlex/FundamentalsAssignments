package com.github.avanlex.fundamentalsassignments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.avanlex.fundamentalsassignments.entities.*
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin

@Database(entities = [MovieEntity::class, MovieGenreJoin::class, MovieActorJoin::class, GenreEntity::class, ActorEntity::class], version = 1)
abstract class DataBase : RoomDatabase(){

	abstract val moviesDao : MoviesDao
	companion object {
		fun create(application: Context): DataBase {
			return Room.databaseBuilder(application,
				DataBase::class.java,
				DbContract.DATABASE_NAME
			).fallbackToDestructiveMigration()
			 .build()
		}
	}
}