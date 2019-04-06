package de.rieckpil.blog;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Book book;

	@Column(nullable = false)
	private String reviewText;

	@Column(nullable = false)
	private String reviewerEmail;

	@Column(nullable = false)
	private Instant reviewDate;

	@Column(nullable = false)
	private Integer stars;
}
