package com.gahgsp.poketlin.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val teams: MutableList<Team> = mutableListOf()
)
