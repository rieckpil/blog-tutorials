package de.rieckpil.blog

import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
        @Id
        @GeneratedValue
        var id: Long?,

        @Column(nullable = false, unique = true)
        val title: String,

        @Column(nullable = false)
        val author: String) {
}