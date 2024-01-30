package de.rieckpil.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
public class BestReviewedBooks {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookId;

  private String bookName;
  private Long totalReviews;
  private BigDecimal avgStars;
  private Integer maxStars;
  private Integer minStars;
}
