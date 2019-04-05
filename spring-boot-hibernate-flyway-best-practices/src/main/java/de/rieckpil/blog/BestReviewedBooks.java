package de.rieckpil.blog;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

import lombok.Data;

@Data
@Entity
@Immutable
public class BestReviewedBooks {

	@Id
	private Long bookId;
	private String bookName;
	private Integer totalReviews;
	private Double avgStars;
	private Integer maxStars;
	private Integer minStars;
}
