package com.gahgsp.poketlin.repository

import com.gahgsp.poketlin.model.TeamPokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamPokemonRepository : JpaRepository<TeamPokemon, Long> {
}