package com.github.avanlex.fundamentalsassignments.movieList.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
data class Actor(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String? = null
) : Parcelable