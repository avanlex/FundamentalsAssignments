package com.github.avanlex.fundamentalsassignments.movieList.domain

import com.github.avanlex.fundamentalsassignments.movieList.data.Movie

interface IMovieListLoader {
    suspend fun getMovies(): List<Movie>
}