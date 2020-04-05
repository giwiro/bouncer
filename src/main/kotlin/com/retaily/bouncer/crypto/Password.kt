package com.retaily.bouncer.crypto

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun encodePassword(pwd: String): String {
    val encoder = BCryptPasswordEncoder()
    return encoder.encode(pwd)
}

fun validatePassword(pwd: String, hash: String): Boolean {
    val encoder = BCryptPasswordEncoder()
    return encoder.matches(pwd, hash)
}