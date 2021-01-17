package com.github.avanlex.fundamentalsassignments.movieList.data.dto

import android.os.Parcelable
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CreditsJson(
		val cast: List<Actor>,
		val id: Int,
) : Parcelable
