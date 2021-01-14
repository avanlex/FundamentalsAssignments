package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditsJson(
		val cast: List<CastItem>,
		val id: Int,
) : Parcelable

@Parcelize
data class CastItem(
	val id: Int,
	val name: String,
	val profilePath: String
) : Parcelable

@Parcelize
data class Actor(
		val id: Int,
		val name: String,

		val picture: String
) : Parcelable