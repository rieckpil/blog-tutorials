package de.rieckpil.blog

import javax.annotation.PostConstruct
import javax.ejb.Singleton
import javax.ejb.Startup
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
@Startup
open class BookService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostConstruct
    open fun setUp() {
        println("Initializing books ...")
        entityManager.persist(Book(null, "Java EE 8", "Duke"))
        entityManager.persist(Book(null, "Jakarta EE 8", "Duke"))
        entityManager.persist(Book(null, "MicroProfile 2.2", "Duke"))
        println("... finished initializing books")
    }

    open fun getAllBooks(): List<Book> = entityManager.createQuery("SELECT b FROM Book b", Book::class.java).resultList
    open fun getBookById(id: Long): Book? = entityManager.find(Book::class.java, id)
}