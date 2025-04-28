package com.gahgsp.poketlin.service

import com.gahgsp.poketlin.model.User
import com.gahgsp.poketlin.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun createUser(username: String, password: String, email: String): User {
        val user = User(username = username, password = password, email = email)
        return userRepository.save(user)
    }
}