package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
        val id: Int,
        val overview: String,
        val title: String,
        val posterPath: String,
        val backdropPath: String,
        val rating: Float,
        val votesCount: Int,
        val adult: Boolean,
        val runtime: Int,
        var favorite: Boolean,
        val genres: List<Genre>,
        val actors: List<Actor>?
) : Parcelable