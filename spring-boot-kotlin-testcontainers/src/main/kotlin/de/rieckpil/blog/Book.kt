package de.rieckpil.blog

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "books")
class Book(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,

  @NaturalId
  var isbn: String,

  @Column(nullable = false)
  var title: String
)
