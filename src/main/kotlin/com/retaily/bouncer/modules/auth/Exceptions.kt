package com.retaily.bouncer.modules.auth

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ExistingEmailException(message:String): RuntimeException(message)

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class WrongCredentialsException(message:String): RuntimeException(message)