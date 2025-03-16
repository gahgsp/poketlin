package com.gahgsp.poketlin.dto

data class TeamPokemonCreateDTO(val pokemonId: Int, val teamId: Long)

data class TeamPokemonResponseDTO(val id: Long, val pokemonId: Int, val team: Long)