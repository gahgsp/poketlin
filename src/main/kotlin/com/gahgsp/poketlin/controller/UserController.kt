package com.gahgsp.poketlin.controller

import com.gahgsp.poketlin.dto.UserCreateDTO
import com.gahgsp.poketlin.dto.UserResponseDTO
import com.gahgsp.poketlin.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping()
    fun createUser(@Valid @RequestBody body: UserCreateDTO): ResponseEntity<UserResponseDTO> {
        val createdUser = userService.createUser(body.username, body.password, body.email)
        val response = UserResponseDTO(createdUser.id!!, createdUser.username, createdUser.email)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}