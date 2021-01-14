package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credits(
	val cast: List<CastItem>,
	val id: Int,
	val crew: List<CrewItem>
) : Parcelable

@Parcelize
data class CastItem(
	val castId: Int,
	val character: String,
	val gender: Int,
	val creditId: String,
	val knownForDepartment: String,
	val originalName: String,
	val popularity: Double,
	val name: String,
	val profilePath: String,
	val id: Int,
	val adult: Boolean,
	val order: Int
) : Parcelable

@Parcelize
data class CrewItem(
	val gender: Int,
	val creditId: String,
	val knownForDepartment: String,
	val originalName: String,
	val popularity: Double,
	val name: String,
	val profilePath: String,
	val id: Int,
	val adult: Boolean,
	val department: String,
	val job: String
) : Parcelable
