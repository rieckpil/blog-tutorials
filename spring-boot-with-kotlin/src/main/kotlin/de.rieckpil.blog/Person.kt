package de.rieckpil.blog

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Person(
  @Id @GeneratedValue var id: Long? = null,
  @Column(nullable = false) var firstname: String,
  @Column(nullable = false) var lastname: String,
  @Column(unique = true, nullable = false) var username: String,
  @Column(nullable = false) var dayOfBirth: LocalDate
)
