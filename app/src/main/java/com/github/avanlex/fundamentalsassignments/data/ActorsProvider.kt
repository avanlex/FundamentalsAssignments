package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.ActorJson

class ActorsProvider(private val api: MovieApi) : IActorProvider {

    override suspend fun loadActors(movieId: Int): List<ActorJson> {
        return api.loadActors(movieId).cast
    }
}