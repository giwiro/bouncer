package com.retaily.bouncer.modules.auth.web

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class CreateUserWebRequest {
    @NotNull(message = "First name cannot be missing or empty")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    val firstName: String? = null

    @NotNull(message = "Last name cannot be missing or empty")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    val lastName: String? = null

    @NotNull(message = "Email cannot be missing or empty")
    @Email
    val email: String? = null

    @NotNull(message = "Password is a required field")
    @Size(min = 6, message = "Password must be equal to or greater than 8 characters")
    val password: String? = null
}

class LoginWebRequest {
    @NotNull(message = "Email cannot be missing or empty")
    @Email
    val email: String? = null

    @NotNull(message = "Password is a required field")
    val password: String? = null
}