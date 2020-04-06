package com.retaily.bouncer.modules.auth

data class CreateUserRequest(val firstName: String, val lastName: String, val email: String, val password: String)

data class GetUserRequest(val id: Long)

data class LoginRequest(val email: String, val password: String)