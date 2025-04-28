package com.gahgsp.poketlin.service

import com.gahgsp.poketlin.dto.TeamPokemonResponseDTO
import com.gahgsp.poketlin.model.TeamPokemon
import com.gahgsp.poketlin.repository.TeamPokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamPokemonService(
    private val teamPokemonRepository: TeamPokemonRepository,
    private val teamService: TeamService
) {

    @Transactional
    fun addPokemonToTeam(pokemonId: Int, teamId: Long): TeamPokemon {
        // TODO: Add check to avoid "null"! Throw an error!
        val team = teamService.getTeamById(teamId)
        val pokemonToAdd = TeamPokemon(pokemonId = pokemonId, team = team!!)
        return teamPokemonRepository.save(pokemonToAdd)
    }
}