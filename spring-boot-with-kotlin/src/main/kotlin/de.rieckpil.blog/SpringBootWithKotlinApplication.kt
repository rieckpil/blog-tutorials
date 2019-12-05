package de.rieckpil.blog

import java.time.LocalDate
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class SpringBootWithKotlinApplication

fun main(args: Array<String>) {
  runApplication<SpringBootWithKotlinApplication>(*args)
}

@Component
class PersonInitializer(
  private val personRepository: PersonRepository
) : CommandLineRunner {

  override fun run(vararg args: String?) {

    val personOne = Person(null, "Mike", "Kotlin", "mk90",
      LocalDate.of(1990, 1, 1))
    val personTwo = Person(null, "Java", "Duke", "jduke",
      LocalDate.of(1995, 1, 1))
    val personThree = Person(null, "Andy", "Fresh", "afresh",
      LocalDate.of(2000, 1, 1))

    personRepository.saveAll(listOf(personOne, personTwo, personThree))
  }
}
