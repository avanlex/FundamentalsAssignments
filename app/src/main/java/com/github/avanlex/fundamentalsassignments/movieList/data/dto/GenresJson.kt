package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import com.github.avanlex.fundamentalsassignments.movieList.data.Genre
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GenresJson(
	val genres: List<Genre>
) : Parcelable
