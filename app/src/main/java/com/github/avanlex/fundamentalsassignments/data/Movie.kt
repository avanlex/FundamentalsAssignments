package com.github.avanlex.fundamentalsassignments.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val poster: String,
        val backdrop: String,
        val ratings: Float,
        val numberOfRatings: Int,
        val minimumAge: Int,
        val runtime: Int,
        var favorite: Boolean,
        val genres: List<Genre>,
        val actors: List<Actor>
) : Parcelable