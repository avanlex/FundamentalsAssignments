package com.github.avanlex.fundamentalsassignments.data

import com.github.avanlex.fundamentalsassignments.movieList.data.Actor

interface IActorDataSource {

    suspend fun loadActors(movieId: Int):  List<Actor>

}