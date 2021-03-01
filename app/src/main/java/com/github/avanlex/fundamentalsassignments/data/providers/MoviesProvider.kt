package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.data.dao.MoviesDao
import com.github.avanlex.fundamentalsassignments.entities.MovieEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieWithGenres
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.MovieJson

class MoviesProvider(private val api: MovieApi, private val moviesDao: MoviesDao) : IMovieProvider {

    override suspend fun loadMovies(): List<MovieJson> {
        return api.loadMovies().movieList
    }

    override suspend fun dbLoadMovies(): List<MovieWithGenres> {
        return moviesDao.getMovies()
    }

    override suspend fun insert(movie: MovieEntity) {
        moviesDao.insert(movie)
    }

    override suspend fun insertList(moviesList: List<MovieEntity>) {
        moviesDao.insertMoviesList(moviesList)
    }

    override suspend fun deleteById(id: Int) {
        moviesDao.deleteById(id)
    }
}