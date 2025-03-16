package com.gahgsp.poketlin.controller

import com.gahgsp.poketlin.api.PokemonAPI
import com.gahgsp.poketlin.dto.TeamCreateDTO
import com.gahgsp.poketlin.dto.TeamResponseDTO
import com.gahgsp.poketlin.model.Team
import com.gahgsp.poketlin.service.TeamService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController()
@RequestMapping("/api/teams")
class TeamController(private val teamService: TeamService, private val pokemonAPI: PokemonAPI) {

    fun Team.getNamesFromPokemons(): List<String> = this.pokemonEntries.map { pokemon ->
        pokemonAPI.getPokemonById(pokemon.pokemonId).name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }

    @PostMapping()
    fun createTeam(@Valid @RequestBody body: TeamCreateDTO): ResponseEntity<TeamResponseDTO> {
        val createdTeam = teamService.createTeam(body.username, body.name, body.description)
        val response = TeamResponseDTO(
            createdTeam.id!!,
            createdTeam.name,
            createdTeam.description,
            createdTeam.pokemonEntries.size,
            createdTeam.createdAt,
            createdTeam.updatedAt
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{teamId}")
    fun viewTeam(@PathVariable teamId: Long): ResponseEntity<TeamResponseDTO> {
        val team = teamService.getTeamById(teamId)
        val pokemons = team!!.getNamesFromPokemons()
        val response = TeamResponseDTO(
            team.id!!,
            team.name,
            team.description,
            team.pokemonEntries.size,
            team.createdAt,
            team.updatedAt,
            pokemons
        )
        return ResponseEntity.ok(response)
    }
}