package com.retaily.bouncer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BouncerApplication

fun main(args: Array<String>) {
	runApplication<BouncerApplication>(*args)
}
