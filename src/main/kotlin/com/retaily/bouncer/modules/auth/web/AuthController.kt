package com.retaily.bouncer.modules.auth.web

import com.retaily.common.web.SessionService
import com.retaily.common.web.Authorized
import com.retaily.bouncer.models.User
import com.retaily.bouncer.modules.auth.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/bouncer/auth"])
class AuthController(
        @Autowired val useCase: AuthUseCase,
        @Autowired val service: SessionService) {

    @PostMapping(path = ["/create-user"])
    fun createUser(@Valid @RequestBody requestBody: CreateUserWebRequest): User? {
        val useCaseRequest = CreateUserRequest(
                requestBody.firstName!!,
                requestBody.lastName!!,
                requestBody.email!!,
                requestBody.password!!
        )
        val createdUser = useCase.createUser(useCaseRequest)
        service.startSession(createdUser.id)
        return createdUser
    }

    @PostMapping(path = ["/login"])
    fun login(@Valid @RequestBody requestBody: LoginWebRequest): User? {
        val useCaseRequest = LoginRequest(requestBody.email!!, requestBody.password!!)
        val user = useCase.login(useCaseRequest)
        service.startSession(user.id)
        return user
    }

    @GetMapping(path = ["/session"])
    @Authorized
    fun getSession(): User? {
        val userId = service.getUserId()
        return useCase.getUser(GetUserRequest(userId!!))
    }

    @PostMapping(path = ["/logout"])
    fun logout() {
        service.finishSession()
    }
}