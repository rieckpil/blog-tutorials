package de.rieckpil.blog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false, unique = true)
  private String isbn;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;
}
