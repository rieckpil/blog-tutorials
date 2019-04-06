package de.rieckpil.blog;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

import lombok.Data;

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
