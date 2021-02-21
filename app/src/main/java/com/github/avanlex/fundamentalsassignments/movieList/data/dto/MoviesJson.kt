package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MoviesJson(
 	@SerialName("results")
	val movieList: List<MovieJson>,
)

@Serializable
data class MovieJson(
	@SerialName("id")
	val movieId: Int,
	@SerialName("overview")
	val overview: String,
	@SerialName("title")
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
	val voteAverage: Float,
	@SerialName("adult")
	val adult: Boolean,
	@SerialName("vote_count")
	val votesCount: Int
)

