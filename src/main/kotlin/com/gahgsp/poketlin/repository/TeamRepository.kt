package com.gahgsp.poketlin.repository

import com.gahgsp.poketlin.model.Team
import com.gahgsp.poketlin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Long> {
    fun findByUser(user: User): List<Team>
    fun findByUserAndId(user: User, id: Long): Team?
}
