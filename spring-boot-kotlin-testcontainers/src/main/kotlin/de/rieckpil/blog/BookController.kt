package de.rieckpil.blog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookRepository: BookRepository) {

  @GetMapping
  @RequestMapping("/books")
  fun getAllBooks(): List<Book> = bookRepository.findAll()
}
