package com.haslett.pokemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: Int,
    val name: String,
    val description: String,
)
