package com.gahgsp.poketlin.service

import com.gahgsp.poketlin.model.Team
import com.gahgsp.poketlin.repository.TeamRepository
import com.gahgsp.poketlin.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TeamService(private val teamRepository: TeamRepository,
                  private val userRepository: UserRepository
) {
    @Transactional
    fun createTeam(username: String, name: String, description: String?): Team {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("User not found")

        val team = Team(
            name = name,
            description = description,
            user = user
        )

        return teamRepository.save(team)
    }

    @Transactional
    fun getTeamById(id: Long): Team? {
        return teamRepository.findByIdOrNull(id)
    }
}