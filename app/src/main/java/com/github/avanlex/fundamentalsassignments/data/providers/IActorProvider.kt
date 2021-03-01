package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.ActorJson

interface IActorProvider {

    suspend fun loadActorsFromNet(movieId: Int):  List<ActorJson>

    suspend fun dbLoadActors(movieId: Int): List<ActorEntity>
    suspend fun dbInsertList(actors: List<ActorEntity>)
    suspend fun dbInsertMovieActorJoin(join: MovieActorJoin)
    suspend fun dbDeleteActor(actorId: Int)
}