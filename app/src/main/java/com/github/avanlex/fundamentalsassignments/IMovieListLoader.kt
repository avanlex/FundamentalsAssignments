package com.github.avanlex.fundamentalsassignments

import com.github.avanlex.fundamentalsassignments.data.Movie

interface IMovieListLoader {
    suspend fun getMovies(): List<Movie>
}