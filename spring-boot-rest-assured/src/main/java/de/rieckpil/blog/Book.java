package de.rieckpil.blog;

public class Book {

  private Long id;
  private String isbn;
  private String title;
  private String author;

  public Book() {
  }

  public Book(Long id, String isbn, String title, String author) {
    this.id = id;
    this.isbn = isbn;
    this.title = title;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
