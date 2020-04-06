package com.retaily.bouncer.modules.auth

import com.retaily.bouncer.common.web.NotInSessionException
import com.retaily.bouncer.crypto.encodePassword
import com.retaily.bouncer.database.entities.UserRepository
import com.retaily.bouncer.models.User
import com.retaily.bouncer.database.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthUseCase constructor(@Autowired val userRepository: UserRepository) {
    fun createUser(request: CreateUserRequest): User {
        val existingUser = userRepository.findByEmail(request.email)
        if (existingUser != null) throw ExistingEmailException("Email already exists")
        val password = encodePassword(request.password)
        val insertUser = UserEntity(request.firstName, request.lastName, request.email, password)
        val insertedUser = userRepository.save(insertUser)
        return insertedUser.mapToModel()
    }

    fun getUser(request: GetUserRequest): User {
        val existingUser = userRepository.findById(request.id).orElse(null)
                ?: throw NotInSessionException("Not in session")
        return existingUser.mapToModel()
    }

    fun login(request: LoginRequest): User {
        val foundUser = userRepository.findByEmail(request.email)
                ?: throw WrongCredentialsException("Wrong credentials")
        return foundUser.mapToModel()
    }
}