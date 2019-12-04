package de.rieckpil.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWithKotlinApplication

fun main(args: Array<String>) {
  runApplication<SpringBootWithKotlinApplication>(*args)
}
