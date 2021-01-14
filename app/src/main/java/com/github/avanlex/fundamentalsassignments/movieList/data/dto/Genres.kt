package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GenresJson(
	val genres: List<GenresItem>
) : Parcelable

@Parcelize
@Serializable
data class GenresItem(
	val name: String,
	val id: Int
) : Parcelable
