package com.github.avanlex.fundamentalsassignments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.avanlex.fundamentalsassignments.domain.*

@Database(entities = [MovieEntity::class, GenreEntity::class, ActorEntity::class, MovieActor::class, MovieGenre::class], version = 1)
abstract class DataBase : RoomDatabase(){

	abstract val movieDao : MoviesDao
	companion object {
		fun create(application: Context): DataBase {
			return Room.databaseBuilder(
				application,
				DataBase::class.java,
				DbContract.DATABASE_NAME
			).fallbackToDestructiveMigration()
			 .build()
		}
	}
}