package com.gahgsp.poketlin.controller

import com.gahgsp.poketlin.dto.TeamCreateDTO
import com.gahgsp.poketlin.dto.TeamResponseDTO
import com.gahgsp.poketlin.service.TeamService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/teams")
class TeamController(private val teamService: TeamService) {
    @PostMapping()
    fun createTeam(@Valid @RequestBody body: TeamCreateDTO): ResponseEntity<TeamResponseDTO> {
        val createdTeam = teamService.createTeam(body.username, body.name, body.description)
        val response = TeamResponseDTO(
            createdTeam.id!!,
            createdTeam.name,
            createdTeam.description,
            createdTeam.pokemonEntries.size,
            createdTeam.createdAt,
            createdTeam.updatedAt)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}