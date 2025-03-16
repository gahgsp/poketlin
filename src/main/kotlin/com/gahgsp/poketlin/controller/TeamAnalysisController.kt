package com.gahgsp.poketlin.controller

import com.gahgsp.poketlin.service.TeamAnalysisService
import com.gahgsp.poketlin.service.TeamService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/teams/{teamId}/analysis")
class TeamAnalysisController(
    private val teamService: TeamService,
    private val teamAnalysisService: TeamAnalysisService
) {
    @GetMapping("/weaknesses")
    fun getTeamWeaknesses(@PathVariable teamId: Long): ResponseEntity<Map<String, Double>> {
        val team = teamService.getTeamById(teamId)
        return ResponseEntity.ok(teamAnalysisService.analyzeTeamWeaknesses(team!!))
    }
}