package de.rieckpil.blog;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookRequest {

  @NotEmpty
  private String title;

  @NotEmpty
  @Size(max = 20)
  private String isbn;

  @NotEmpty
  private String author;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
