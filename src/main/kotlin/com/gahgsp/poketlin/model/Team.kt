package com.gahgsp.poketlin.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="teams")
data class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pokemonEntries: MutableList<TeamPokemon> = mutableListOf(),

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
