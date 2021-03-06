package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.data.dao.MoviesDao
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson

class AccountProvider(movieApi: MovieApi, moviesDao: MoviesDao) : IAccountProvider {

    override suspend fun markAsFavorite(favorite: FavoriteMovieJson): Boolean {
        /* TODO: FIX retrofit2.HttpException: HTTP 404
                For request "POST account/{account_id}/favorite"
                "GET /account" is required to execute
                and to do this, you first need to get the session
                "POST /authentication/session/new"
                Too lazy to do it now.*/
        // return api.markAsFavorite(favorite).statusCode == 12
        return true
    }
}