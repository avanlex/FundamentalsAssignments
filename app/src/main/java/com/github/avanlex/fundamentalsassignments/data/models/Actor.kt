package com.github.avanlex.fundamentalsassignments.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class  Actor(
    val name: String,
    val avatar: String,
) : Parcelable