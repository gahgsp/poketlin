package com.gahgsp.poketlin.model

import jakarta.persistence.*

@Entity
@Table(name = "team_pokemon")
data class TeamPokemon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val pokemonId: Int, // External PokéAPI ID

    @Column(nullable = true)
    val nickname: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    val team: Team,

    @Column
    val level: Int = 50
)