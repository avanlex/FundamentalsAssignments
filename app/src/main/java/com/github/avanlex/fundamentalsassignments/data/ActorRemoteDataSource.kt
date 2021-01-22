package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.FavoriteMovieJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorRemoteDataSource(private val api: MovieApi) : IActorDataSource {

    override suspend fun loadActors(movieId: Int):  List<Actor> = withContext(Dispatchers.IO) {
        val actors = api.loadActors(movieId).cast
        actors.map { Actor(it.id, it.name, BuildConfig.BASE_IMAGE_URL + "original" + it.picture) }
    }
}