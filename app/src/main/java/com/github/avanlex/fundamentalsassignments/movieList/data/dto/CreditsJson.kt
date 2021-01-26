package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsJson(
		val cast: List<ActorJson>,
		val id: Int,
)

@Serializable
data class ActorJson(
		val id: Int,
		val name: String,
		@SerialName("profile_path")
		val picture: String? = null
)