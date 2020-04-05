package com.retaily.bouncer.modules.auth

import com.retaily.bouncer.crypto.encodePassword
import com.retaily.bouncer.database.entities.UserRepository
import com.retaily.bouncer.models.User
import com.retaily.bouncer.database.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UseCase constructor(@Autowired val userRepository: UserRepository) {
    fun createUser(request: CreateUserRequest): User {
        val password = encodePassword(request.password)
        val insertUser = UserEntity(request.firstName, request.lastName, request.email, password)
        val insertedUser = userRepository.save(insertUser)
        return insertedUser.mapToModel()
    }

    fun login(request: LoginRequest): User? {
        val foundUser = userRepository.findByEmail(request.email)
        return foundUser?.mapToModel()
    }
}