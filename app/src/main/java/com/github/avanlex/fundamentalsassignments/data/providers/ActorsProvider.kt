package com.github.avanlex.fundamentalsassignments.data.providers

import com.github.avanlex.fundamentalsassignments.data.dao.ActorsDao
import com.github.avanlex.fundamentalsassignments.entities.ActorEntity
import com.github.avanlex.fundamentalsassignments.entities.relations.MovieActorJoin
import com.github.avanlex.fundamentalsassignments.movieList.data.MovieApi
import com.github.avanlex.fundamentalsassignments.movieList.data.dto.ActorJson

class ActorsProvider(private val api: MovieApi, private val actorsDao: ActorsDao) : IActorProvider {

    override suspend fun loadActorsFromNet(movieId: Int): List<ActorJson> {
        return api.loadActors(movieId).cast
    }

    override suspend fun dbLoadActors(movieId: Int): List<ActorEntity> {
        return actorsDao.getByMovieId(movieId)
    }

    override suspend fun dbInsertList(actors: List<ActorEntity>){
        actorsDao.insertList(actors)
    }

    override suspend fun dbInsertMovieActorJoin(join: MovieActorJoin){
        actorsDao.insertMovieActorJoin(join)
    }

    override suspend fun dbDeleteActor(actorId: Int){
        return actorsDao.deleteById(actorId)
    }
}
