package de.rieckpil.blog

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Person(
  @Id @GeneratedValue var id: Long? = null,
  @Column(nullable = false) var firstname: String,
  @Column(nullable = false) var lastname: String,
  @Column(unique = true, nullable = false) var username: String,
  @Column(nullable = false) var dayOfBirth: LocalDate,
)
