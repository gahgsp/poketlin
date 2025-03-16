package com.gahgsp.poketlin.api

import com.gahgsp.poketlin.exception.ResourceNotFoundException
import com.gahgsp.poketlin.model.Pokemon
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class PokemonAPI(private val restTemplate: RestTemplate) {

    @Value("\${pokeapi.base-url:https://pokeapi.co/api/v2}")
    private lateinit var baseURL: String // Tells the compiler that a non-null property will be initialized before it's used, but not at declaration time.

    @Cacheable("pokemon")
    fun getPokemonById(id: Int): Pokemon {
        return restTemplate.getForObject("$baseURL/pokemon/$id")
            ?: throw ResourceNotFoundException("Pokémon with ID $id not found.")
    }

    @Cacheable("pokemon")
    fun getPokemonByName(name: String): Pokemon {
        return restTemplate.getForObject("$baseURL/pokemon/${name.lowercase()}")
            ?: throw ResourceNotFoundException("Pokémon with name $name not found.")
    }
}