package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Genre(
    val id: Int,
    val name: String
) : Parcelable