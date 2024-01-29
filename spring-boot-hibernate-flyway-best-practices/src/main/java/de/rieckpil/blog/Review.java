package de.rieckpil.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Book book;

  @Column(nullable = false)
  private String reviewText;

  @Column(nullable = false)
  private String reviewerEmail;

  @Column(nullable = false)
  private Instant reviewDate;

  @Column(nullable = false)
  private Integer stars;
}
