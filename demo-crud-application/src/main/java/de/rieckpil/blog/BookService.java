package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Long createNewBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());

        book = bookRepository.save(book);

        return book.getId();
    }

}
