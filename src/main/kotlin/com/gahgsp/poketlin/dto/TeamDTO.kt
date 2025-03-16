package com.gahgsp.poketlin.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class TeamCreateDTO(
    @field:NotBlank(message = "Username is required")
    val username: String,

    @field:NotBlank(message = "Team name is required")
    @field:Size(min = 1, max = 50, message = "Team name must be between 1 and 50 characters")
    val name: String,

    val description: String? = null
)

data class TeamResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val pokemonCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val pokemons: List<String>? = listOf()
)