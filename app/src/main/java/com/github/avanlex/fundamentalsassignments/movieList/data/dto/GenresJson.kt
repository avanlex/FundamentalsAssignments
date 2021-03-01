package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresJson(
	val genres: List<GenreJson>
)

@Serializable
data class GenreJson(
	@SerialName("id")
	val genreId: Int,
	val name: String
)
