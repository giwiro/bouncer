package com.retaily.bouncer.modules.auth.web

import com.retaily.bouncer.modules.auth.CreateUserRequest
import com.retaily.bouncer.modules.auth.UseCase
import com.retaily.bouncer.models.User
import com.retaily.bouncer.modules.auth.LoginRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/auth"])
class Controller constructor(@Autowired val useCase: UseCase) {
    @PostMapping(path = ["/create-user"])
    fun createUser(@Valid @RequestBody requestBody: CreateUserWebRequest): User? {
        val request = CreateUserRequest(
                requestBody.firstName!!,
                requestBody.lastName!!,
                requestBody.email!!,
                requestBody.password!!
        )
        return useCase.createUser(request)
    }

    @PostMapping(path = ["/login"])
    fun login(@Valid @RequestBody requestBody: LoginWebRequest): User? {
        val request = LoginRequest(requestBody.email!!, requestBody.password!!)
        return useCase.login(LoginRequest(request.email, request.password))
    }
}