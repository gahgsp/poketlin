package com.gahgsp.poketlin.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
    val sprites: PokemonSprites
)

data class PokemonType(val slot: Int, val type: NamedApiResource)
data class PokemonStat(val baseStat: Int, val effort: Int, val stat: NamedApiResource)
data class PokemonAbility(val isHidden: Boolean, val slot: Int, val ability: NamedApiResource)
data class PokemonSprites(val frontDefault: String?)
data class NamedApiResource(val name: String, val url: String)
