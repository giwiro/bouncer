package com.retaily.bouncer.modules.auth.web

import com.retaily.bouncer.common.web.SessionService
import com.retaily.bouncer.common.web.Authorized
import com.retaily.bouncer.common.web.NotInSessionException
import com.retaily.bouncer.models.User
import com.retaily.bouncer.modules.auth.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/bouncer/auth"])
class Controller(
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
        service.startSession(createdUser)
        return createdUser
    }

    @PostMapping(path = ["/login"])
    fun login(@Valid @RequestBody requestBody: LoginWebRequest): User? {
        val useCaseRequest = LoginRequest(requestBody.email!!, requestBody.password!!)
        val user = useCase.login(useCaseRequest)
        service.startSession(user)
        return user
    }

    @GetMapping(path = ["/get-session"])
    fun getSession(): User? {
        val userId = service.getUserId() ?: throw NotInSessionException("Not in session")
        return useCase.getUser(GetUserRequest(userId))
    }

    @PostMapping(path = ["/logout"])
    fun logout() {
        service.finishSession()
    }

    @GetMapping(path = ["/test"])
    @Authorized
    fun test(): String {
        return "Test"
    }
}