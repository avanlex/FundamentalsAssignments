package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Actor(
    val id: Int,
    val name: String,
    @SerialName("results")
    val picture: String
) : Parcelable