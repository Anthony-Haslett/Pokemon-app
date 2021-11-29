package com.haslett.pokemon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val id: Int,
    val name: String,
    val description: String,
)
