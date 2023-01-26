package org.koenighotze.cooking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookingApplication

fun main(args: Array<String>) {
    runApplication<CookingApplication>(*args)
}
