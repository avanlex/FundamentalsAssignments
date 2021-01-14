package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MoviesJson(
//	val page: Int,
//	val totalPages: Int,
	@SerialName("results")
	val movieList: List<MovieJson>,
//	val totalMovies: Int
) : Parcelable

@Serializable
@Parcelize
data class MovieJson(
	val id: Int,
	val overview: String,
	val title: String,
	@SerialName("genre_ids")
	val genreIds: List<Int>,
	@SerialName("poster_path")
	val posterPath: String,
	@SerialName("backdrop_path")
	val backdropPath: String,
	@SerialName("release_date")
	val releaseDate: String,
	@SerialName("vote_average")
	val voteAverage: Double,
	val adult: Boolean,
	@SerialName("vote_count")
	val votesCount: Int
) : Parcelable

