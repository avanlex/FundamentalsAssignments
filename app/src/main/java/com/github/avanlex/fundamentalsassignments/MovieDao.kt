package com.github.avanlex.fundamentalsassignments

import android.content.ContentProvider
import android.content.Context
import com.github.avanlex.fundamentalsassignments.data.Movie
import com.github.avanlex.fundamentalsassignments.data.loadMovies
import javax.inject.Inject

class MovieDao @Inject constructor(
    val context : Context
) {
    suspend fun getById(id: Int): Movie? {
        val movies = loadMovies(context)
        return movies.find{it.id == id}
    }

    suspend fun getAllMovies(): List<Movie> {
        return loadMovies(context)
    }
}
