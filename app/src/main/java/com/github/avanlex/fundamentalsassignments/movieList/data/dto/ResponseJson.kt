package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseJson(
	val statusMessage: String? = null,
	val statusCode: Int? = null
)

