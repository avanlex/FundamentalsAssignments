package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.data.dao.GenresDao
import com.github.avanlex.fundamentalsassignments.entities.GenreEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieGenreJoin
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.GenreJson

class GenresProvider(private val api: MovieApi, private val genresDao: GenresDao) : IGenresProvider {

    override suspend fun loadGenres(): List<GenreJson> {
        return api.loadGenres().genres
    }

    override suspend fun dbInsertList(genres: List<GenreEntity>) {
        genresDao.insertList(genres)
    }

    override suspend fun dbInsertMovieGenreJoin(join: MovieGenreJoin) {
        genresDao.insertMovieGenreJoin(join)
    }
}

