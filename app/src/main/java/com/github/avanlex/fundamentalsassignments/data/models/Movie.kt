package com.github.avanlex.fundamentalsassignments.data.models

data class Movie(
    val name: String,
    val year: Int,
    val duration: Int,
    val pg: String,
    val rating: Int,
    val reviewCount: Int,
    val storyline: String,
    val poster: String
)