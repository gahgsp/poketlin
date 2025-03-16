package com.gahgsp.poketlin.service

import com.gahgsp.poketlin.api.PokemonAPI
import com.gahgsp.poketlin.model.Pokemon
import com.gahgsp.poketlin.model.Team
import org.springframework.stereotype.Service

@Service
class TeamAnalysisService(private val pokemonAPI: PokemonAPI) {

    fun Team.getPokemonDetails(): List<Pokemon> = this.pokemonEntries.map { pokemonAPI.getPokemonById(it.pokemonId) }

    fun analyzeTeamWeaknesses(team: Team): Map<String, Double> {
        val pokemons = team.getPokemonDetails()

        return ALL_TYPES.associateWith { attackingType ->
            pokemons.map { pokemon ->
                // Accumulates (reduce) the effectiveness multiplier for this Pokémon.
                pokemon.types.fold(1.0)
                { acc, pokemonType ->
                    val defenderType = pokemonType.type.name
                    acc * (TYPE_EFFECTIVENESS[attackingType]?.get(defenderType) ?: 1.0)
                }
            }.average() // We calculate the average vulnerability for this Team.
        }.filter { it.value > 1.0 } // We only want the Types where our Team is weak against.
            .entries.sortedWith(compareByDescending { it.value }).associate { it.key to it.value }
    }

    fun analyzeTeamStats(team: Team): Map<String, Double> {
        val pokemonList = team.getPokemonDetails()

        return STAT_NAMES.associateWith { statName ->
            pokemonList.map { pokemon ->
                pokemon.stats.find { it.stat.name == statName }?.baseStat ?: 0
            }.average()
        }
    }

    fun analyzeTypeDiversity(team: Team): Map<String, Int> {
        return team.getPokemonDetails()
            .flatMap { pokemon -> pokemon.types.map { it.type.name } }
            .groupingBy { it }
            .eachCount()
    }

    // Allows the definition of class-level functions and properties.
    // Good for holding constants and shared utilities.
    companion object {
        /**
         * Defines the values for the effectiveness of each type against each other.
         * It follows the structure: atk -> def -> effectiveness multiplier.
         */
        private val TYPE_EFFECTIVENESS = mapOf(
            "normal" to mapOf("rock" to 0.5, "ghost" to 0.0, "steel" to 0.5),
            "fire" to mapOf(
                "fire" to 0.5,
                "water" to 0.5,
                "grass" to 2.0,
                "ice" to 2.0,
                "bug" to 2.0,
                "rock" to 0.5,
                "dragon" to 0.5,
                "steel" to 2.0
            ),
            "water" to mapOf(
                "fire" to 2.0,
                "water" to 0.5,
                "grass" to 0.5,
                "ground" to 2.0,
                "rock" to 2.0,
                "dragon" to 0.5
            ),
            "electric" to mapOf(
                "water" to 2.0,
                "electric" to 0.5,
                "grass" to 0.5,
                "ground" to 0.0,
                "flying" to 2.0,
                "dragon" to 0.5
            ),
            "grass" to mapOf(
                "fire" to 0.5,
                "water" to 2.0,
                "grass" to 0.5,
                "poison" to 0.5,
                "ground" to 2.0,
                "flying" to 0.5,
                "bug" to 0.5,
                "rock" to 2.0,
                "dragon" to 0.5,
                "steel" to 0.5
            ),
            "ice" to mapOf(
                "fire" to 0.5,
                "water" to 0.5,
                "grass" to 2.0,
                "ice" to 0.5,
                "ground" to 2.0,
                "flying" to 2.0,
                "dragon" to 2.0,
                "steel" to 0.5
            ),
            "fighting" to mapOf(
                "normal" to 2.0,
                "ice" to 2.0,
                "poison" to 0.5,
                "flying" to 0.5,
                "psychic" to 0.5,
                "bug" to 0.5,
                "rock" to 2.0,
                "ghost" to 0.0,
                "dark" to 2.0,
                "steel" to 2.0,
                "fairy" to 0.5
            ),
            "poison" to mapOf(
                "grass" to 2.0,
                "poison" to 0.5,
                "ground" to 0.5,
                "rock" to 0.5,
                "ghost" to 0.5,
                "steel" to 0.0,
                "fairy" to 2.0
            ),
            "ground" to mapOf(
                "fire" to 2.0,
                "electric" to 2.0,
                "grass" to 0.5,
                "poison" to 2.0,
                "flying" to 0.0,
                "bug" to 0.5,
                "rock" to 2.0,
                "steel" to 2.0
            ),
            "flying" to mapOf(
                "electric" to 0.5,
                "grass" to 2.0,
                "fighting" to 2.0,
                "bug" to 2.0,
                "rock" to 0.5,
                "steel" to 0.5
            ),
            "psychic" to mapOf("fighting" to 2.0, "poison" to 2.0, "psychic" to 0.5, "dark" to 0.0, "steel" to 0.5),
            "bug" to mapOf(
                "fire" to 0.5,
                "grass" to 2.0,
                "fighting" to 0.5,
                "poison" to 0.5,
                "flying" to 0.5,
                "psychic" to 2.0,
                "ghost" to 0.5,
                "dark" to 2.0,
                "steel" to 0.5,
                "fairy" to 0.5
            ),
            "rock" to mapOf(
                "fire" to 2.0,
                "ice" to 2.0,
                "fighting" to 0.5,
                "ground" to 0.5,
                "flying" to 2.0,
                "bug" to 2.0,
                "steel" to 0.5
            ),
            "ghost" to mapOf("normal" to 0.0, "psychic" to 2.0, "ghost" to 2.0, "dark" to 0.5),
            "dragon" to mapOf("dragon" to 2.0, "steel" to 0.5, "fairy" to 0.0),
            "dark" to mapOf("fighting" to 0.5, "psychic" to 2.0, "ghost" to 2.0, "dark" to 0.5, "fairy" to 0.5),
            "steel" to mapOf(
                "fire" to 0.5,
                "water" to 0.5,
                "electric" to 0.5,
                "ice" to 2.0,
                "rock" to 2.0,
                "steel" to 0.5,
                "fairy" to 2.0
            ),
            "fairy" to mapOf(
                "fire" to 0.5,
                "fighting" to 2.0,
                "poison" to 0.5,
                "dragon" to 2.0,
                "dark" to 2.0,
                "steel" to 0.5
            )
        )

        /**
         * Defines a list of all the available Pokémon types.
         */
        val ALL_TYPES = listOf(
            "normal", "fire", "water", "electric", "grass", "ice",
            "fighting", "poison", "ground", "flying", "psychic", "bug",
            "rock", "ghost", "dragon", "dark", "steel", "fairy"
        )

        /**
         * Defines a list of all the basic stats for a given Pokémon.
         */
        val STAT_NAMES = listOf(
            "hp", "attack", "defense", "special-attack", "special-defense", "speed"
        )
    }
}