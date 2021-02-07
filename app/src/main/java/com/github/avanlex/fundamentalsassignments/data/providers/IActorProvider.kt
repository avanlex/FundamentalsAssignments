package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.movieList.data.dto.ActorJson

interface IActorProvider {

    suspend fun loadActors(movieId: Int):  List<ActorJson>

}