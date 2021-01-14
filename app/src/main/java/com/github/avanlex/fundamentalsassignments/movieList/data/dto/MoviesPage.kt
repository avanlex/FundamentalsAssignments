package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MoviesPage(
//	val page: Int,
//	val totalPages: Int,
	@SerialName("results")
	val movieList: List<MovieItem>
//	val totalMovies: Int
) : Parcelable

@Serializable
@Parcelize
data class MovieItem(
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
	val voteCount: Int
) : Parcelable
