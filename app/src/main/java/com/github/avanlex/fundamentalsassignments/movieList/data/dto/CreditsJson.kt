package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsJson(
	@SerialName("id")
	val movieId: Int,
	val cast: List<ActorJson>,
)

@Serializable
data class ActorJson(
	@SerialName("id")
	val actorId: Int,
	val name: String,
	@SerialName("profile_path")
	val profilePath: String? = null
)