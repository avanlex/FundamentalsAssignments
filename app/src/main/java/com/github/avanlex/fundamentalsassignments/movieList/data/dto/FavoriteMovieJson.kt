package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteMovieJson(
	@SerialName("media_id")
	val mediaId: Int? = null,
	val favorite: Boolean? = null,
	@SerialName("media_type")
	val mediaType: String? = null
)

