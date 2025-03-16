package com.gahgsp.poketlin.controller

import com.gahgsp.poketlin.dto.TeamPokemonCreateDTO
import com.gahgsp.poketlin.dto.TeamPokemonResponseDTO
import com.gahgsp.poketlin.service.TeamPokemonService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/teams/pokemon")
class TeamPokemonController(private val teamPokemonService: TeamPokemonService) {
    @PostMapping("/add")
    fun addPokemonToTeam(@Valid @RequestBody body: TeamPokemonCreateDTO): ResponseEntity<TeamPokemonResponseDTO> {
        val addedPokemon = teamPokemonService.addPokemonToTeam(pokemonId = body.pokemonId, teamId = body.teamId)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            TeamPokemonResponseDTO(
                id = addedPokemon.id!!,
                pokemonId = addedPokemon.pokemonId,
                team = addedPokemon.team.id!!
            )
        )
    }
}