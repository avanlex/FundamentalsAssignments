package com.github.avanlex.fundamentalsassignments
import javax.inject.Inject

class MoviesRepository constructor(
    private val movieDAO: MovieDao
) {
    suspend fun getById(id: Int) = movieDAO.getById(id)
    suspend fun getAllMovies() = movieDAO.getAllMovies()
}
