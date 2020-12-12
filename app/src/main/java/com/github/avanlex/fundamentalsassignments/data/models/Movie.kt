package com.github.avanlex.fundamentalsassignments.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val year: Int,
    val tagline: String,
    val duration: Int,
    val pg: String,
    val rating: Int,
    val reviewCount: Int,
    val storyline: String,
    val poster: Int,
    val actors: List<Actor>
) : Parcelable